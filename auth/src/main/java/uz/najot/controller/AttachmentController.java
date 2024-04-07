package uz.najot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.najot.confing.PrincipleUser;
import uz.najot.model.FileDTO;
import uz.najot.service.AttachmentService;

import java.util.List;

/**
 * @description: TODO
 * @date: 25 March 2024 $
 * @time: 6:02 PM 40 $
 * @author: Qudratjon Komilov
 */
@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
@Slf4j
public class AttachmentController {

    private final AttachmentService attachmentService;


    @PostMapping("/upload")
    public List<FileDTO> upload(MultipartHttpServletRequest request, @AuthenticationPrincipal PrincipleUser principleUser) {
        return attachmentService.upload(request, principleUser);
    }

    @GetMapping("/download/{id}")
    public HttpEntity<?> download(@PathVariable Long id) {
        return attachmentService.download(id);
    }


}
