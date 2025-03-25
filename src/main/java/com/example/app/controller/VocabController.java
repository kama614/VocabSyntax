package com.example.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Vocab;
import com.example.app.domain.VocabType;
import com.example.app.mapper.VocabMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/syntax/vocabs")
public class VocabController {

	private final VocabMapper mapper;

	// 単語リストの取得
	@GetMapping
	public ResponseEntity<List<Vocab>> getVocabs() {
		List<Vocab> vocabs = mapper.selectAll();
		return new ResponseEntity<>(vocabs, HttpStatus.OK);
	}

	// IDに基づく単語の取得
	@GetMapping("/{id}")
	public ResponseEntity<Vocab> getVocabsById(@PathVariable int id) {
		Vocab vocab = mapper.selectById(id);
		HttpStatus status = vocab == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

		return new ResponseEntity<>(vocab, status);
	}

	// 種別IDに基づく単語リストの取得
	@GetMapping("/type/{typeId}")
	public ResponseEntity<List<Vocab>> getVocabsByType(
			@PathVariable int typeId) {
		List<Vocab> vocabs = mapper.selectByType(typeId);
		return new ResponseEntity<>(vocabs, HttpStatus.OK);
	}

	// 種別リストの取得
	@GetMapping("/types")
	public ResponseEntity<List<VocabType>> getVocabTypes() {
		List<VocabType> vocabTypes = mapper.selectAllTypes();
		return new ResponseEntity<>(vocabTypes, HttpStatus.OK);
	}

	// 単語の追加
	@PostMapping
	public ResponseEntity<String> addVocab(
			@RequestBody @Valid Vocab vocab,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>("failed to add vocab",
					HttpStatus.BAD_REQUEST);
		}
		mapper.insert(vocab);
		return new ResponseEntity<>("succeeded to add vocab", HttpStatus.OK);
	}

	// 単語の編集
	@PutMapping
	public ResponseEntity<String> updateVocab(
			@RequestBody @Valid Vocab vocab,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>("filed to update vocab",
					HttpStatus.BAD_REQUEST);
		}
		mapper.update(vocab);
		return new ResponseEntity<>("succeeded to update vocab",
				HttpStatus.OK);
	}

	// 単語の削除
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVocab(@PathVariable int id) {
		mapper.delete(id);
		return new ResponseEntity<>("succeeded to delete vocab",
				HttpStatus.OK);
	}

}
