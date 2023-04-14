package ua.com.alevel.facade;

import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.dto.UserFinanceDTO;

import java.util.List;

public interface UserFacade {

    //+
    List<UserDTO> findAll();
    UserFinanceDTO findById(Long id);
    void create(UserDTO dto);
    void update(Long id, UserDTO dto);
    void delete(Long id);
}
