// 単語情報
package com.example.app.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Vocab {

	private Integer id;

	@NotBlank
	@Size(max = 1000)
	private String word;

	@NotBlank
	@Size(max = 1000)
	private String meaning;

	private VocabType vocabType;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registeredAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

}

/*
 @JsonFormat
 Jackson（JSON を扱うライブラリ）を使うときに、日付や時刻のフォーマットを指定するためのアノテーション
✅ timezone = "Asia/Tokyo" を付けると JST（日本時間）に変換 できる。
✅ Date だけでなく LocalDateTime にも適用可能。
✅ JSON の出力時も入力時もフォーマットが統一されるので便利。
 
 
 */
