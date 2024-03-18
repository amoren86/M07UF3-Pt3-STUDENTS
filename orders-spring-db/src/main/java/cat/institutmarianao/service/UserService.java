package cat.institutmarianao.service;

import cat.institutmarianao.domain.User;
import jakarta.validation.constraints.NotBlank;

public interface UserService {
	User get(@NotBlank String username);
}
