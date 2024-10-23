package com.djamware.school.study.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.djamware.school.study.clients.LecturerClient;
import com.djamware.school.study.clients.StudentClient;
import com.djamware.school.study.clients.dtos.LecturerResponseDto;
import com.djamware.school.study.clients.dtos.StudentResponseDto;
import com.djamware.school.study.dtos.StudyRequestDto;
import com.djamware.school.study.model.Student;
import com.djamware.school.study.model.Study;
import com.djamware.school.study.repositories.StudentRepository;
import com.djamware.school.study.repositories.StudyRepository;
import static com.djamware.school.study.utils.Constants.BAD_REQUEST;
import static com.djamware.school.study.utils.Constants.CODE_200_VAL;
import static com.djamware.school.study.utils.Constants.CODE_201_VAL;
import static com.djamware.school.study.utils.Constants.CODE_KEY;
import static com.djamware.school.study.utils.Constants.DATA_KEY;
import static com.djamware.school.study.utils.Constants.DATA_NOT_FOUND;
import static com.djamware.school.study.utils.Constants.LIDN_KEY;
import static com.djamware.school.study.utils.Constants.MESSAGE_KEY;
import static com.djamware.school.study.utils.Constants.MESSAGE_VAL;
import static com.djamware.school.study.utils.Constants.SIDN_KEY;
import static com.djamware.school.study.utils.Constants.STUDY_END_TIME_KEY;
import static com.djamware.school.study.utils.Constants.STUDY_NAME_KEY;
import static com.djamware.school.study.utils.Constants.STUDY_START_TIME_KEY;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;

@ApplicationScoped
public class StudyService {
    StudyRepository studyRepository;
    StudentRepository studentRepository;

    @RestClient
    StudentClient studentClient;

    @RestClient
    LecturerClient lecturerClient;

    @Channel("study-activity")
    Emitter<String> emitterSendStudyActivity;

    ObjectMapper mapper = new ObjectMapper();

    @Inject
    public StudyService(StudentRepository studentRepository, StudyRepository studyRepository,
            ObjectMapper mapper) {
        this.studentRepository = studentRepository;
        this.studyRepository = studyRepository;
        this.mapper = mapper;
    }

    public JsonObject saveStudy(StudyRequestDto req) throws ValidationException, JsonProcessingException {
        if (req == null)
            throw new ValidationException(BAD_REQUEST);

        Study newStudy = new Study();
        newStudy.setStudyName(req.getStudyName());
        newStudy.setStudyDescription(req.getStudyDescription());
        newStudy.setStudyDate(LocalDate.parse(req.getStudyDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(new Locale("id", "ID"))));

        LecturerResponseDto lecturer = lecturerClient.getLecturerByLidn(req.getLidn());
        newStudy.setLecturerId(lecturer.getLidn());
        newStudy.setLecturerName(lecturer.getName());

        List<Student> students = new ArrayList<>();

        req.getSidn().stream().forEach(sidn -> {
            StudentResponseDto student = studentClient.getStudentBySidn(sidn);
            Student newStudent = new Student();
            newStudent.setSidn(student.getSidn());
            newStudent.setName(student.getName());
            studentRepository.persist(newStudent);
            students.add(newStudent);
        });

        newStudy.setStudents(students);
        studyRepository.persist(newStudy);

        // Send message to Kafka
        Map<String, Object> payload = new HashMap<>();
        payload.put(SIDN_KEY, req.getSidn());
        payload.put(LIDN_KEY, req.getLidn());
        payload.put(STUDY_NAME_KEY, req.getStudyName());
        payload.put(STUDY_START_TIME_KEY, req.getStudyDate());
        payload.put(STUDY_END_TIME_KEY, req.getStudyDate());
        emitterSendStudyActivity.send(mapper.writeValueAsString(payload));

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_201_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

    public JsonObject updateStudy(StudyRequestDto req, ObjectId id) throws ValidationException {
        if (req == null)
            throw new ValidationException(BAD_REQUEST);

        Study study = studyRepository.findById(id);
        if (study == null)
            throw new ValidationException(DATA_NOT_FOUND);

        LecturerResponseDto lecturer = lecturerClient.getLecturerByLidn(req.getLidn());
        List<Student> students = new ArrayList<>();

        req.getSidn().stream().forEach(sidn -> {
            StudentResponseDto student = studentClient.getStudentBySidn(sidn);
            Student newStudent = new Student();
            newStudent.setSidn(student.getSidn());
            newStudent.setName(student.getName());
            studentRepository.persist(newStudent);
            students.add(newStudent);
        });

        studyRepository.update(
                "studyName = ?1, studyDescription = ?2, studyDate = ?3, lecturerId = ?4, lecturerName = ?5, students = ?6 where id = ?7",
                req.getStudyName(), req.getStudyDescription(), LocalDate.parse(req.getStudyDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(new Locale("id", "ID"))),
                lecturer.getLidn(), lecturer.getName(), students, id);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

    public JsonObject getListStudy() {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, studyRepository.listAll());

        return resp;
    }

    public JsonObject getStudyByID(ObjectId id) {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, studyRepository.findById(id));

        return resp;
    }

    public JsonObject deleteStudy(ObjectId id) throws ValidationException {
        if (id == null)
            throw new ValidationException(BAD_REQUEST);

        Study study = studyRepository.findById(id);
        if (study != null)
            studyRepository.delete(study);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }
}
