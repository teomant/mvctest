package org.teomant.controller;


import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.teomant.controller.form.AttemptForm;
import org.teomant.controller.form.LoginForm;
import org.teomant.controller.form.RegistrationForm;
import org.teomant.controller.validator.AttemptValidator;
import org.teomant.entity.AttemptEntity;
import org.teomant.entity.GameEntity;
import org.teomant.entity.UserEntity;
import org.teomant.repository.GameRepository;
import org.teomant.repository.UserRepository;
import org.teomant.service.AttemptService;
import org.teomant.service.GameService;
import org.teomant.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class PagesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    AttemptService attemptService;

    @ModelAttribute( "loginForm" )
    public LoginForm loginForm(){
        return new LoginForm();
    }

    @ModelAttribute( "registrationForm" )
    public RegistrationForm registrationForm(){
        return new RegistrationForm();
    }

    @ModelAttribute("attemptForm")
    public AttemptForm attemptForm() {return new AttemptForm();}

    @Autowired
    AttemptValidator attemptValidator;

    @InitBinder("attemptForm")
    protected void initAttemptBinder( WebDataBinder binder ){ binder.setValidator( attemptValidator ); }

    @GetMapping(value = "/game")
    public String getGamePage(Model model,
                              @CookieValue( name = "userID" )
                                      Long userId) {
        if( userId == null ) return "redirect:/";
        UserEntity userEntity = userService.getUserById(userId).get();
        model.addAttribute("user",userEntity);
        return "gamePage";

    }

    @PostMapping(value = "/game")
    public String postGamePage(@Validated
                               @ModelAttribute( "attemptForm" )
                                       AttemptForm attemptForm, BindingResult errors, Model model,
                                @CookieValue( name = "userID" )
                                           Long userId) {
        if( errors.hasErrors() ){
            model.addAttribute("user", userService.getUserById(userId).get());
            return "gamePage";
        }
        if( userId == null ) return "redirect:/";
        UserEntity userEntity = userService.getUserById(userId).get();
        GameEntity gameEntity = userEntity.getGames().get(userEntity.getGames().size()-1);
        AttemptEntity attemptEntity = new AttemptEntity();
        attemptEntity.setGame(gameEntity);
        attemptEntity.setValue(attemptForm.getAttempt());
        long bull=0;
        long cow=0;
        for (int i=0;i<attemptForm.getAttempt().length();i++)
        {
            if (i==gameEntity.getTask().indexOf(attemptForm.getAttempt().charAt(i))) {
                bull++;
            }else if (gameEntity.getTask().indexOf(attemptForm.getAttempt().charAt(i))>=0){
                cow++;
            }
        }
        attemptEntity.setCow(cow);
        attemptEntity.setBull(bull);
        if (bull==gameEntity.getTask().length()){
            attemptEntity.setCorrect(true);
            gameEntity.setFinished(true);
            gameRepository.saveAndFlush(gameEntity);
        } else {
            attemptEntity.setCorrect(false);
        }
        attemptService.addAttempt(attemptEntity);

        model.addAttribute("user", userService.getUserById(userId).get());
        return "gamePage";

    }

    @PostMapping(value = "/newGame")
    public String newGame(Model model,
                          @CookieValue( name = "userID" )
                                  Long userId) {
        if( userId == null ) return "redirect:/";
        UserEntity userEntity = userService.getUserById(userId).get();
        GameEntity gameEntity = new GameEntity();
        gameEntity.setFinished(false);
        gameEntity.setTask(getNewTask());
        gameEntity.setPlayer(userEntity);
        gameService.addGame(gameEntity);

        return "redirect:/game";
    }

    @GetMapping
    public String index(Model model) {
        return "indexPage";
    }

    @PostMapping(value = "/login")
    public String login(Model model,
                        HttpServletResponse response,
                        @ModelAttribute( "loginForm" )
                                LoginForm loginForm) {

        Optional<UserEntity> opt = userRepository.getUserByUsername(loginForm.getUsername());
        if (opt.isPresent()){
            if (opt.get().getPassword().equals(loginForm.getPass())){
                Cookie userid = new Cookie("userID",String.valueOf(opt.get().getId()));
                userid.setMaxAge(( int ) Duration.ofHours( 3 ).getSeconds());
                response.addCookie(userid);
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
        return "redirect:/game";
    }

    @GetMapping(value = "/registration")
    public String getRegistration(Model model) {
        return "registrationPage";

    }

    @PostMapping(value = "/registration")
    public String postRegistration(Model model,
                        HttpServletResponse response,
                        @ModelAttribute( "registrationForm" )
                                RegistrationForm registrationForm) {

        Optional<UserEntity> opt = userRepository.getUserByUsername(registrationForm.getUsername());
        if (opt.isPresent()){
            return "redirect:/registration";
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registrationForm.getUsername());
            userEntity.setPassword(registrationForm.getPass());
            UserEntity newUser = userRepository.saveAndFlush(userEntity);
            GameEntity gameEntity = new GameEntity();
            gameEntity.setTask(getNewTask());
            gameEntity.setFinished(false);
            gameEntity.setPlayer(newUser);
            gameService.addGame(gameEntity);
            Cookie userid = new Cookie("userID",String.valueOf(newUser.getId()));
            userid.setMaxAge(( int ) Duration.ofHours( 3 ).getSeconds());
            response.addCookie(userid);
            return "redirect:/game";
        }
    }

    private String getNewTask(){
        int[] ints = new Random().ints(0, 10).distinct().limit(4).toArray();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            strBuilder.append(ints[i]);
        }
        return strBuilder.toString();
    }

    @GetMapping(value = "/logout")
    public String logout(Model model,
                         HttpServletResponse response) {
        response.addCookie(new Cookie("userID",null));
        return "redirect:/";
    }

    @GetMapping(value = "/statistic/{id}")
    public String statistic(Model model,
                         @PathVariable
                            Long id) {
        UserEntity user = userService.getUserById(id).get();
        int games = user.getGames().size();
        int finishedGames = user.getGames().stream().filter(gameEntity -> gameEntity.isFinished())
                .collect(Collectors.toList()).size();
        long attempts = user.getGames().stream().
                mapToLong(userEntity -> {return userEntity.getAttempts().size();}).sum();
        model.addAttribute("id",id);
        model.addAttribute("games",games);
        model.addAttribute("finishedGames",finishedGames);
        model.addAttribute("attempts",attempts);
        return "statisticPage";
    }

    @GetMapping( value = "/test", produces = "application/json" )
    public @ResponseBody
    String getAllMessagesOfGroup( HttpServletRequest request) {
        System.out.println(request.getParameter("number"));
        System.out.println("test");

        return JSONParser.quote("success");
    }
}
