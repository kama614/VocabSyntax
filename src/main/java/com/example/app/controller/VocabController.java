// データベース上の「単語（Vocab）」と「単語の種類（VocabType）」を管理する REST API
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

@RequiredArgsConstructor // Lombokを使い、finalフィールド（VocabMapper）のコンストラクタを自動生成
@RestController //  REST API を提供
@RequestMapping("/syntax/vocabs")
public class VocabController {

	private final VocabMapper mapper; // MyBatisのMapper インターフェース で、データベースとやり取りするために使用
	// mapperを通じてデータを取得・更新・削除するのに記述

	// 単語リストの取得(全ての単語を取得し、リストを返す)
	@GetMapping
	public ResponseEntity<List<Vocab>> getVocabs() {
		List<Vocab> vocabs = mapper.selectAll();
		return new ResponseEntity<>(vocabs, HttpStatus.OK);
	}

	// 指定されたIDの単語を取得(ID が見つからなければ 404 NOT FOUND を返す)
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

	// 単語の追加(@Valid によりバリデーションを実行し、エラーがあれば 400 BAD REQUESTを返す)
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

	// 単語の編集(@Valid によりバリデーションを実行し、エラーがあれば 400 BAD REQUESTを返す)
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

/*
・Spring Boot の REST API コントローラーとして動作。
・データの取得・追加・更新・削除（CRUD） を行う。
・MyBatis の VocabMapper を使ってデータベース操作を行う。
・バリデーションを @Valid で実施し、不正なデータを防ぐ。

★ResponseEntityを使う理由
１．HTTP ステータスコードを自由に変更できる
通常の return obj; だと 200 OK 固定 になってしまうが、
ResponseEntity を使えば、例えば 404 Not Found や 400 Bad Request を明示的に設定できる。

２．レスポンスヘッダーを設定できる
ResponseEntity を使うと、カスタムヘッダーを追加 できる。

３．エラーハンドリングを柔軟にできる
例えば、データが見つからなかったときに 404 Not Found を返すことが簡単にできる。

@PathVariable
URL のパス（path）に含まれる値を取得 するための Spring のアノテーション。
REST API のエンドポイントに動的な値を渡すとき に使用。
@RequestParam との違い → @PathVariable は リソースを識別、@RequestParam は フィルタリングや検索 に使う。

@RequestBody
リクエストボディ（JSON）を Java オブジェクトに変換する。
✅ @Valid と組み合わせると、入力チェック（バリデーション） ができる。
✅ 配列（List）も受け取れる (List<Vocab> vocabs)。
✅ @RequestParam とは異なり、URL のクエリではなく JSON 形式でデータを送信 するのに適している。

 */











