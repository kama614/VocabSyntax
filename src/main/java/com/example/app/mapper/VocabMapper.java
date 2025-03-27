// vocab_typesとvocabsテーブルまとめて記述(規模が大きいなら1テーブルに1Mapperで作成する)
package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Vocab;
import com.example.app.domain.VocabType;

@Mapper
public interface VocabMapper {

	List<Vocab> selectAll();

	Vocab selectById(int id);

	List<Vocab> selectByType(int typeId);

	List<VocabType> selectAllTypes();

	void insert(Vocab vocab);

	void update(Vocab vocab);

	void delete(int id);

}

/*
@Mapper
MyBatis のマッパーインターフェース を示すアノテーション。
このアノテーションを付けることでSQLとJavaのデータ変換を自動化できる。

@Mapper のメリット
✅ SQL を直接書ける ので、JPA よりも 細かいクエリの制御が可能。
✅ Spring Boot が自動でマッパーを実装してくれる ので、手動で DAO クラスを書く手間が省ける。
✅ データベースのカラムと Java のオブジェクトをマッピング できる。

@Mapper使うときの記述方法は二通り
１．Mapperに直接SQL文を記述
@Select("SQL文")、@Insert、@Update、@Delete
型 メソッド名

２．XMLファイルにSQL文を記述
xmlを使うときはMapperクラスに@Mapperつけてメソッド名のみの記述
✅ XML に SQL を書くことで、複雑なクエリの管理がしやすくなる。


List<Vocab> 
複数のデータを取得するメソッドに使う
データベースの SELECT * FROM vocabs; の結果が 複数の行 になるため、
それを List<Vocab> にマッピングする。

Vocab 
１件のデータを取得するメソッドに使う
データベースの SELECT * FROM vocabs WHERE id = ?; の結果が1行だけ返るため、
それを Vocab というオブジェクトに変換する。

void
データベースの更新処理（INSERT, UPDATE, DELETE）を実行するだけで、戻り値は不要


*/