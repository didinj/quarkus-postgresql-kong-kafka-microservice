package com.djamware.school.lecturer.services;

import com.djamware.school.lecturer.dtos.LecturerRequestDto;
import com.djamware.school.lecturer.models.Lecturer;
import com.djamware.school.lecturer.repositories.LecturerRepository;
import static com.djamware.school.lecturer.utils.Constants.BAD_REQUEST;
import static com.djamware.school.lecturer.utils.Constants.CODE_200_VAL;
import static com.djamware.school.lecturer.utils.Constants.CODE_201_VAL;
import static com.djamware.school.lecturer.utils.Constants.CODE_KEY;
import static com.djamware.school.lecturer.utils.Constants.DATA_KEY;
import static com.djamware.school.lecturer.utils.Constants.DATA_NOT_FOUND;
import static com.djamware.school.lecturer.utils.Constants.MESSAGE_KEY;
import static com.djamware.school.lecturer.utils.Constants.MESSAGE_VAL;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class LecturerService {
    LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Transactional
    public JsonObject saveLecturer(LecturerRequestDto req) throws ValidationException {
        if (req == null)
            throw new ValidationException(BAD_REQUEST);

        Lecturer lecturer = new Lecturer();
        lecturer.lidn = req.getLidn();
        lecturer.name = req.getName();
        lecturer.email = req.getEmail();
        lecturer.phone = req.getPhone();
        lecturer.major = req.getMajor();
        lecturerRepository.persist(lecturer);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_201_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

    @Transactional
    public JsonObject updateLecturer(LecturerRequestDto req, Long id) throws ValidationException {
        if (req == null)
            throw new ValidationException(BAD_REQUEST);

        Lecturer lecturer = lecturerRepository.findById(id);
        if (lecturer == null)
            throw new ValidationException(DATA_NOT_FOUND);

        lecturerRepository.update("lidn = ?1, name = ?2, email = ?3, phone = ?4, major = ?5 where id = ?6",
                req.getLidn(),
                req.getName(), req.getEmail(), req.getPhone(), req.getMajor(), id);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }

    public JsonObject getListLecturer() {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, lecturerRepository.listAll());

        return resp;
    }

    public JsonObject getLecturerByID(Long id) {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, lecturerRepository.findById(id));

        return resp;
    }

    public JsonObject getLecturerByLidn(String lidn) {
        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);
        resp.put(DATA_KEY, lecturerRepository.findByLidn(lidn));

        return resp;
    }

    @Transactional
    public JsonObject deleteLecturer(Long id) throws ValidationException {
        if (id == null)
            throw new ValidationException(BAD_REQUEST);

        Lecturer lecturer = lecturerRepository.findById(id);
        if (lecturer != null)
            lecturerRepository.delete(lecturer);

        JsonObject resp = new JsonObject();
        resp.put(CODE_KEY, CODE_200_VAL);
        resp.put(MESSAGE_KEY, MESSAGE_VAL);

        return resp;
    }
}
