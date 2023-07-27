package com.groupe3.apirestquizodk3.controllers;

import com.group3.apirestquiz.models.Question;
import com.group3.apirestquiz.models.Result;
import com.group3.apirestquiz.services.QuestionService;
import com.group3.apirestquiz.services.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users/{userId}/quizzes/{quizId}/")
public class PlayQuizController {


}
