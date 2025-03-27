// Spring Boot の CORS（Cross-Origin Resource Sharing）設定
// フロントエンドのReactアプリのリクエストを許可
package com.example.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Springの設定クラスを示す
public class ApplicationConfig implements WebMvcConfigurer {
	// Spring MVC の設定をカスタマイズするために WebMvcConfigurer を実装

	@Override
	public void addCorsMappings(CorsRegistry registry) { // CorsRegistry を使って CORS の設定を行う
		registry
				.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("Content-Type")
				.allowCredentials(true);
	}

}

/*
.addMapping("/**")	
すべてのエンドポイント（URLパス）に対して CORS を適用

.allowedOrigins("http://localhost:3000")	
http://localhost:3000 からのリクエストのみ許可
★ここの設定はセキュリティ上必須。("*")にするとセキュリティリスクが高まる。

.allowedMethods("GET", "POST", "PUT", "DELETE")	
許可する HTTP メソッドを指定

.allowedHeaders("Content-Type")	
許可するヘッダーを指定

.allowCredentials(true)	
認証情報（クッキーなど）を含むリクエストを許可

↑
フロントエンド（React, Vue など）とバックエンド（Spring Boot）を
別々のサーバーで動かす場合、CORS 設定を行うことで、異なるオリジンからのリクエストを許可できる

API を外部のクライアントが利用する場合
(モバイルアプリや他のウェブアプリがバックエンドの API を利用する際、適切なオリジンを指定することでアクセスを許可できる)
*/