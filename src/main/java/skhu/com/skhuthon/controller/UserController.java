package skhu.com.skhuthon.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import skhu.com.skhuthon.domain.User;
import skhu.com.skhuthon.service.UserService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestParam("loginId") String loginId, @RequestParam("pw") String pw) {
        System.out.println("/login");
        System.out.println(loginId + ", " + pw);

        User user = userService.findByIdAndPw(loginId, pw);

        if (user == null) {
            session.setAttribute("access", false);
            return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body("failed");
        } else {
            session.setAttribute("access", true);
            session.setAttribute("name", user.getName());
            return ResponseEntity.ok("ok");
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newUser(HttpSession session, @RequestParam("loginId") String loginId, @RequestParam("pw") String pw, @RequestParam("name") String name) {
        User user = userService.findByIdAndPw(loginId, pw);

        if (user == null) {// 가능
            userService.addUser(loginId, pw, name);

            session.setAttribute("access", true);

            return ResponseEntity.ok("ok");
        } else {// 불가능
            return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body("이미 있음");
        }
    }
}
