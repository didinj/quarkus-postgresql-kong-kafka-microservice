package com.djamware.school.student.services;

import com.djamware.school.student.dtos.StudentRequestDto;
import com.djamware.school.student.models.Student;
import com.djamware.school.student.repositories.StudentRepository;
import static com.djamware.school.student.utils.Constants.BAD_REQUEST;
import static com.djamware.school.student.utils.Constants.CODE_200_VAL;
import static com.djamware.school.student.utils.Constants.CODE_201_VAL;
import static com.djamware.school.student.utils.Constants.CODE_KEY;
import static com.djamware.school.student.utils.Constants.DATA_KEY;
import static com.djamware.school.student.utils.Constants.DATA_NOT_FOUND;
import static com.djamware.school.student.utils.Constants.MESSAGE_KEY;
import static com.djamware.school.student.utils.Constants.MESSAGE_VAL;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;

@ApplicationScoped
public class StudentService {
    StudentRepository studentRepository;

    @Inject
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public JsonObject saveStudent(StudentRequestDto req) throws ValidationException {
        if (req == null)
            throw new ValidationException(BAD_REQUEST);

        Student student = new Student();
        student.sidn = req.getSidn();
        student.name = req.getName();
        student.email = req.getEmail();
        student.phone = req.getPhone();
        studentRepository.persist(student);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_201_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

    @Transactional
    public JsonObject updateStudent(StudentRequestDto req, Long id) throws ValidationException {
        if (req == null)
            throw new ValidationException(BAD_REQUEST);

        Student student = studentRepository.findById(id);
        if (student == null)
            throw new ValidationException(DATA_NOT_FOUND);

        studentRepository.update("sidn = ?1, name = ?2, email = ?3, phone = ?4 where id = ?5", req.getSidn(),
                req.getName(), req.getEmail(), req.getPhone(), id);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

    public JsonObject getListStudent() {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, studentRepository.listAll());

        return resp;
    }

    public JsonObject getStudentByID(Long id) {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, studentRepository.findById(id));

        return resp;
    }

    public JsonObject getStudentBySidn(String sidn) {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, studentRepository.findBySidn(sidn));

        return resp;
    }

    @Transactional
    public JsonObject deleteStudent(Long id) throws ValidationException {
        if (id == null)
            throw new ValidationException(BAD_REQUEST);

        Student student = studentRepository.findById(id);
        if (student != null)
            studentRepository.delete(student);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

}
