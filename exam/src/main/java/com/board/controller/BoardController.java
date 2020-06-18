package com.board.controller;

import com.board.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.service.BoardService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/board/write.do")
    public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {
        if (idx == null) {
            model.addAttribute("board", new BoardDTO());
        } else {
            BoardDTO board = boardService.getBoardDetail(idx);
            if (board == null) {
                return "redirect:/board/list.do";
            }
            model.addAttribute("board", board);
        }

        return "board/write";
    }
}