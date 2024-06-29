package skhu.com.skhuthon.service;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import skhu.com.skhuthon.domain.User;
import skhu.com.skhuthon.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByIdAndPw(String id, String pw) {
        return userRepository.findByLoginIdAndPw(id, pw);
    }

    public ResponseEntity<Object> addUser(String loginId, String pw, String name) {
        // 이미 존재하는지 검사
        User existingUser = userRepository.findByLoginId(loginId);

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body("이미 존재하는 사용자입니다.");
        }

        // 존재하지 않으면 사용자 추가
        User newUser = new User(loginId, pw, name);

        userRepository.save(newUser);
        return ResponseEntity.ok("사용자가 성공적으로 추가되었습니다.");
    }

}