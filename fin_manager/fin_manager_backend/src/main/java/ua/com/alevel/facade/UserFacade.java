package ua.com.alevel.facade;

import ua.com.alevel.dto.UserDTO;
import ua.com.alevel.dto.UserAccountsDTO;

import java.util.List;

public interface UserFacade {

    //+
    List<UserDTO> findAll();
//    UserAccountsDTO findById(Long id);
    void create(UserDTO dto);
    void update(Long id, UserDTO dto);
    void delete(Long id);
}
