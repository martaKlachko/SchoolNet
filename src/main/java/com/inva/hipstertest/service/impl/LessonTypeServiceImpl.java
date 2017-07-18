package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.domain.LessonType;
import com.inva.hipstertest.repository.LessonTypeRepository;
import com.inva.hipstertest.service.LessonTypeService;
import com.inva.hipstertest.service.dto.LessonTypeDTO;
import com.inva.hipstertest.service.mapper.LessonTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing LessonType.
 */
@Service
public class LessonTypeServiceImpl implements LessonTypeService{

    private final Logger log = LoggerFactory.getLogger(LessonTypeServiceImpl.class);

    private final LessonTypeRepository lessonTypeRepository;

    private final LessonTypeMapper lessonTypeMapper;

    public LessonTypeServiceImpl(LessonTypeRepository lessonTypeRepository, LessonTypeMapper lessonTypeMapper) {
        this.lessonTypeRepository = lessonTypeRepository;
        this.lessonTypeMapper = lessonTypeMapper;
    }

    /**
     * Save a lessonType.
     *
     * @param lessonTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    @Transactional
    public LessonTypeDTO save(LessonTypeDTO lessonTypeDTO) {
        log.debug("Request to save LessonType : {}", lessonTypeDTO);
        LessonType lessonType = lessonTypeMapper.lessonTypeDTOToLessonType(lessonTypeDTO);
        lessonType = lessonTypeRepository.save(lessonType);
        return lessonTypeMapper.lessonTypeToLessonTypeDTO(lessonType);
    }

    /**
     *  Get all the lessonTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<LessonTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LessonTypes");
        Page<LessonType> result = lessonTypeRepository.findAll(pageable);
        return result.map(lessonTypeMapper::lessonTypeToLessonTypeDTO);
    }

    /**
     *  Get one lessonType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public LessonTypeDTO findOne(Long id) {
        log.debug("Request to get LessonType : {}", id);
        LessonType lessonType = lessonTypeRepository.findOne(id);
        return lessonTypeMapper.lessonTypeToLessonTypeDTO(lessonType);
    }

    /**
     *  Delete the  lessonType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LessonType : {}", id);
        lessonTypeRepository.delete(id);
    }

    /**
     *  Get all the lessonTypes.
     *
     *  @return the list of entities
     */
    @Override
    public List<LessonTypeDTO> findAll() {
        log.debug("Request to get all LessonTypes");
        List<LessonType> lessonTypeList = lessonTypeRepository.findAll();
        return lessonTypeMapper.lessonTypesToLessonTypeDTOs(lessonTypeList);
    }
}
