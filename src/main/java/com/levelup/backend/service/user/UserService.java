package com.levelup.backend.service.user;

import com.levelup.backend.api.model.dto.UserCreateDto;
import com.levelup.backend.api.model.dto.UserFilterDto;
import com.levelup.backend.api.model.dto.UserUpdateDto;
import com.levelup.backend.api.rest.exception.ResourceNotFoundException;
import com.levelup.backend.data.access.repository.RoleRepository;
import com.levelup.backend.data.access.repository.UserRepository;
import com.levelup.backend.data.access.specification.UserSpecification;
import com.levelup.backend.data.entity.Role;
import com.levelup.backend.data.entity.User;
import com.levelup.backend.data.enums.RoleName;
import com.levelup.backend.service.converter.PaginationDtoWithFieldsMappingToPageableConverter;
import com.levelup.backend.service.converter.model.PaginationDtoWithFieldsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoToPageableConverter;

    @Autowired
    public UserService(final UserRepository userRepository, final RoleRepository roleRepository, final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.paginationDtoToPageableConverter = paginationDtoConverter;
    }

    public Page<User> getPage(final UserFilterDto userFilterDto) {
        final Specification<User> specification = UserSpecification.filterBy(userFilterDto);

        final Pageable pageable = paginationDtoToPageableConverter.convert(new PaginationDtoWithFieldsMapping(userFilterDto));
        Objects.requireNonNull(pageable);

        return userRepository.findAll(specification, pageable);
    }

    public User findById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User createUser(final UserCreateDto userCreateDto) {
        Objects.requireNonNull(userCreateDto);

        final Role role = new Role();
        role.setName(RoleName.USER);

        final User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(role);

        return userRepository.save(user);
    }

    public User updateUser(final Long id, final UserUpdateDto userUpdateDto) {
        Objects.requireNonNull(userUpdateDto);

        Role role = roleRepository.findById(userUpdateDto.getRole()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User user = findById(id);
        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());
        user.setRole(role);
        return userRepository.save(user);
    }
}
