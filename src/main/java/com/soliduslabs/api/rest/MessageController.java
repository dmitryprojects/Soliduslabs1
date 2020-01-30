package com.soliduslabs.api.rest;

import com.soliduslabs.domain.Message;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.soliduslabs.service.MessageService;
import com.soliduslabs.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/*
 * Message RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/messages")
@Api(tags = {"messages"})
public class MessageController extends AbstractRestHandler {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a message resource.", notes = "Returns the URL of the new resource in the Location header.")
    public String createMessage(@RequestBody Message message,
                                 HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {

        String digest = Config.toHexString(Config.getSHA(message.getMessage()));
        Message createdMessage = this.messageService.createMessage(message);

        response.setHeader("Location", request.getRequestURL().append("/").append(digest).toString());
        return createdMessage.toDigestString(digest);
    }

    @RequestMapping(value = "/{digest}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Get a single message.", notes = "You have to provide a valid message ID.")
    public
    @ResponseBody
    Message getMessageByDigest(@ApiParam(value = "The digest of the message.", required = true)
                       @PathVariable("digest") String digest,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Message> messages = this.messageService.getAll();
        for (Message m : messages) {
            String d = Config.toHexString(Config.getSHA(m.getMessage()));
            if (d.equals(digest)) {
                response.setStatus(200);
                return m;
            }
        }
        response.setStatus(404);
        return new Message("Message not found");
    }
}
