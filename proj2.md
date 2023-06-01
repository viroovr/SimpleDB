이 '10,000 Line 프로젝트'의 목적은 대규모 프로젝트 코드를 이해하고 그 중 일부를 수정하고 개선하는 것입니다. 회사에 합류하고 해당 프로젝트에 참여하게 되면, 첫 번째로 회사 프로젝트의 방대한 코드를 이해해야 하며, 그런 다음 일부 모듈을 구현해야 합니다.

따라서 이 프로젝트에서는 SimpleDB 프로젝트를 얻어 설치하고, 사용하는 데 익숙해지는 것이 필요합니다. SimpleDB는 회사에서 프로젝트에 사용하는 특정 데이터베이스 시스템 또는 소프트웨어일 것입니다. 코드를 얻고 설치하며 사용 방법을 숙달함으로써, SimpleDB의 내부 동작 방식을 이해하고 프로젝트의 특정 모듈을 수정하거나 개선할 수 있게 됩니다.

총괄적으로, '10,000 Line 프로젝트'의 주요 목표는 다음과 같습니다:

  SimpleDB 프로젝트 코드를 얻습니다.
  시스템에 SimpleDB를 설치합니다.
  기존 코드와 구조에 익숙해집니다.
  SimpleDB의 기능과 사용법을 이해합니다.
  수정 또는 개선이 필요한 특정 모듈이나 영역을 식별합니다.
  해당 모듈에 필요한 변경 사항이나 개선 사항을 구현합니다.
  변경 사항이 의도한 대로 작동하는지 테스트하고 검증합니다.
  프로젝트 팀이나 관련 이해 관계자와 협력하여 변경 사항을 전체 프로젝트 코드베이스에 통합합니다.
이 프로젝트를 성공적으로 완료함으로써 복잡한 코드베이스를 이해하고 작업하는 데 귀중한 경험을 쌓을 수 있으며, 회사 프로젝트에 실질적인 기여를 할 수 있게 됩니다.

설치 방법은 다음과 같습니다:

1. 프로젝트 코드는 In-Ui-Ye-Ji 클러스터의 /home/swe3003/SimpleDB에 있습니다. 다음 Linux 명령을 실행하여 전체 디렉토리를 작업 디렉토리로 복사해야 합니다.
   ```
   $ cp -R /home/swe3003/SimpleDB ~/
   ```

2. 편의를 위해 프로젝트 디렉토리를 환경 변수로 설정하는 것이 좋습니다. 다음과 같이 .bashrc 파일에서 환경 변수를 설정하십시오.
   ```
   $ echo 'export SDBHOME=~/SimpleDB' >> ~/.bashrc
   ```

3. 소스 코드는 src/main/java 디렉토리에 있으며, derbyclient, simpleclient, simpledb 세 개의 하위 디렉토리가 있습니다. simpledb 디렉토리에는 DBMS의 소스 코드가 포함되어 있습니다.

4. simpledb 디렉토리에는 buffer, file 등의 하위 디렉토리가 있으며, 각 모듈의 소스 코드가 해당 디렉토리에 위치합니다.

5. simpleclient 디렉토리에는 클라이언트 코드가 있습니다. 일부 클라이언트 프로그램에는 하드코딩된 SQL 문이 포함되어 있으며, 데이터베이스 엔진을 테스트하고 디버깅하는 데 도움이 될 것입니다.

6. 또한, SimpleIJ.java는 대화형 SQL 쉘용 클라이언트 프로그램입니다. SimpleDB에는 두 가지 유형의 클라이언트가 있습니다. 첫 번째 그룹은 embedded 디렉토리에 위치하며, JDBC를 사용하지 않고 직접 데이터베이스에 액세스합니다. 이를 임베디드 모드라고 합니다. 두 번째 그룹은 network 디렉토리에 위치하며, 클라이언트 프로세스가 SimpleDB 엔진 프로세스와 JDBC를 사용하여 통신하는 서버-클라이언트 모드로 작동합니다. 이 모드를 사용하려면 클라이언트 프로그램을 실행하기 전에 simpledb.server.StartServer 프로그램을 먼저 실행해야 합니다. 각 모드의 사용 방법에 대한 자세한 지침은 계속해서 읽어보십시오.

7. 서버 및 클라이언트를 컴파일하려면 다음과 같이 Maven 명령을 실행해야 합니다. Apache Maven은 Java 기반 소프트웨어 프로젝트의 빌드 자동화 및 프로젝트 관리 도구로, C/C++ 프로젝트의
Makefile이나 CMake과 유사한 기능을 제공합니다. pom.xml 파일을 사용하여 프로젝트의 빌드, 종속성 및 문서를 관리할 수 있습니다. 관련된 pom.xml 파일은 확인하시기 바랍니다. 단, 이 프로젝트에서는 pom.xml을 수정할 필요가 없습니다.
   ```
   $ cd $SDBHOME
   $ mvn compile
   $ mvn package
   ```
컴파일이 완료되면 target 디렉토리에 simpledb-project-1.0.0.jar 파일이 생성됩니다.

JAR(Java Archive) 파일은 Java 클래스 파일과 관련된 메타데이터 및 이미지, 오디오 파일, 속성 파일과 같은 리소스를 포함하는 패키지 파일 형식입니다. Java 애플리케이션이나 라이브러리를 배포하는 데 사용되며, Java 실행 환경(JRE)이 설치된 모든 시스템에서 실행할 수 있습니다.

JAR 파일은 사실상 ZIP 형식을 사용하는 압축된 아카이브 파일입니다. jar tvf jar_file_name 명령을 사용하여 JAR 파일의 내용을 확인할 수 있습니다. 이 명령은 JAR 파일 내에 포함된 파일 및 디렉토리를 목록으로 나열하며, 각각의 경로와 크기도 함께 표시됩니다.

-----------

### 3.1. 내장형 클라이언트 프로그램 실행

#### 3.1.1 내장형 모드에서 student 데이터베이스 생성
CreateStudentDB.java를 실행하기 전에 스캔해주세요. 완전히 이해하지 못해도 괜찮습니다. 이 프로그램은 studentdb라는 이름의 데이터베이스를 생성합니다. 데이터베이스 스키마에 익숙해지는 것이 중요합니다. CreateStudentDB를 실행하려면 다음과 같이 입력하세요.
```
$ cd $SDBHOME/target
$ java -cp ./simpledb-project-1.0.0.jar simpleclient.embedded.CreateStudentDB
```
target 디렉토리에 studentdb 데이터베이스를 위한 하위 디렉토리가 생성되어야 합니다. 내용을 살펴보는 것은 자유롭지만 (단, DB 파일은 바이너리 파일입니다. :D).

이제 StudentMajor.java를 읽어보세요. 이 프로그램은 SQL 쿼리를 실행합니다. 이 프로그램을 실행하려면 다음 명령을 실행하세요.
```
$ java -cp ./simpledb-project-1.0.0.jar simpleclient.embedded.StudentMajor
```
학생들의 이름과 전공이 표시된 9개의 레코드가 출력될 것입니다.

ChangeMajor.java도 읽어야 합니다. 이 프로그램은 STUDENT 테이블에서 Amy의 MajorId 값을 변경하는 쿼리를 실행합니다. 이 프로그램을 실행하려면 다음 명령을 실행하세요.
```
$ java -cp ./simpledb-project-1.0.0.jar simpleclient.embedded.ChangeMajor
```
Amy의 MajorID가 변경되었는지 확인하려면 다시 StudentMajor 프로그램을 실행해야 합니다.
```
$ java -cp ./simpledb-project-1.0.0.jar simpleclient.embedded.StudentMajor
```

#### 3.2 student 데이터베이스 삭제
데이터베이스 테이블을 삭제하려면 studentdb 하위 디렉토리에 있는 파일을 삭제해야 합니다. 다음과 같이 입력하세요.
```
$ cd $SDBHOME/target/studentdb
$ rm -i *.tbl
```
rm 명령을 실행할 때 주의하세요. 소스 코드와 같은 중요한 파일을 실수로 삭제할 수 있습니다. 파일을 삭제하기 전에 확인을 요청하는 -i 옵션을 사용하는 것이 좋습니다. 이 간단한 단계로 데이터 손실을 예방할 수 있습니다.

### 4. 서버로서 SimpleDB 엔진 실행하기
SimpleDB를 서버-클라이언트 모드로 실행할 수 있습니다. 서버를 실행하려면 다음 명령을 실행하세요.
```
$ cd $SDBHOME/target
$ java -cp ./simpledb-project-1.0.0.jar simpledb.server.StartServer
```
서버-클라이언트 모드에서 클라이언트를 실행하는 동안에는 이 서버가 계속 실행되도록 유지해야 합니다.

서버를 중지하려면 CTRL+C를 눌러야 합니다. 사용하지 않을 때에는 반드시 서버를 종료해야 합니다. In-Ui-Ye-Ji는 공유 클러스터이므로, 다른 학생들을 위해 컴퓨팅 리소스를 낭비하지 않도록 주의해야 합니다.

#### 4.1 서버 기반 클라이언트 프로그램 실행
src/main/java/simpleclient/network 디렉토리의 소스 코드를 살펴보세요. 서버가 실행 중일 때 CreateStudentDB와 StudentMajor 클라이언트를 실행할 수 있습니다. 이것은 내장 모드에서 실행할 때와 동일한 출력을 생성할 것입니다.

### 5. 대화형 쉘인 SimpleIJ 실행하기
대화형 쉘에서 직접 SQL 문을 실행하려면 다음 명령을 실행하세요.
```
$ cd $SDBHOME/target
$ java -cp ./simpledb-project-1.0.0.jar simpleclient.SimpleIJ
```
Connect> 프롬프트에서 "jdbc:simpledb:studentdb"를 입력하여 내장 데이터베이스에 연결합니다.
```
Connect>
jdbc:simpledb:studentdb
SQL>
```
이제 SQL 쉘은 여러 번에 걸쳐 SQL 쿼리를 입력하라고 요청할 것입니다. 한 줄에 하나의 SQL 쿼리를 입력하세요. 다음 쿼리를 입력하면 모든 학생의 이름과 전공 ID가 출력될 것입니다.
```
SQL> select sname, majorid from student
```
쉘을 종료하려면 "exit"를 입력하세요.
```
SQL> exit
$
```
Connect> 프롬프트에서 "jdbc:simpledb://localhost"를 입력하면 서버가 여전히 실행 중이라면 SimpleDB 엔진 서버에 연결될 것입니다.

---

## Task 1: Buffer Manager 개선하기
SimpleDB의 버퍼 매니저에는 두 가지 주요한 비효율성이 있습니다:
1. 대체할 버퍼를 찾을 때 첫 번째로 unpinned 상태인 버퍼를 사용하는 대신 LRU와 같은 더 똑똑한 방법을 사용하지 않습니다.
2. 버퍼가 이미 있는지 확인하기 위해 순차 검색을 수행하며, 데이터 구조(예: 맵)를 사용하여 빠르게 버퍼를 찾지 않습니다.

이러한 문제를 해결하기 위해 BufferMgr 클래스를 다음 전략을 사용하여 수정해야 합니다:
1. unpinned 상태인 버퍼의 목록을 유지합니다. 대체 버퍼가 필요할 때는 목록의 맨 앞에서 버퍼를 제거하고 사용합니다. 버퍼의 pin 카운트가 0이 되면 목록의 끝에 추가합니다. 이렇게 하면 LRU 대체가 구현됩니다.
2. 할당된 버퍼를 포함하는 블록을 키로 사용하여 맵을 유지합니다. 버퍼는 null이 아닌 내용을 갖고 있을 때 할당됩니다. 버퍼는 pin 또는 unpinned 상태일 수 있습니다. 버퍼는 할당되지 않은 상태로 시작하며, 처음으로 블록에 할당되면 할당됩니다. 이 맵을 사용하여 블록이 현재 버퍼에 있는지 여부를 확인합니다. 버퍼가 처음 할당될 때는 맵에 추가해야 합니다. 버퍼가 대체될 때는 맵을 변경해야 합니다 - 이전 블록에 대한 매핑을 제거하고 새 블록에 대한 매핑을 추가해야 합니다.
3. 이제 더 이상 필요하지 않으므로 bufferpool 배열을 제거합니다.

또한, Buffer 클래스를 수정하여 각 Buffer 객체가 자신의 버퍼 ID를 알 수 있도록 해야 합니다. 구체적으로, 생성자에 버퍼의 ID를 나타내는 세 번째 인수가 있어야 하며, getId() 메서드가 해당 ID를 반환해야 합니다.

BufferMgr 클래스는 또한 현재 상태를 표시하는 printStatus 메서드를 가져야 합니다. 상태는 할당된 맵의 각 버퍼의 ID, 블록 및 pin 상태, 그리고 unpinned 목록의 각 버퍼의 ID로 구성됩니다. 다음은 네 개의 버퍼를 가진 데이터베이스에서 "test" 파일의 블록 0부터 3까지가 pinned되었다가 블록 2와 0이 unpinned된 경우에 printStatus 메서드가 생성해야 할 출력의 예입니다:

```
Allocated Buffers:
Buffer 1: [file test, block 1] pinned
Buffer 0: [file test, block 0] unpinned
Buffer 3: [file test, block 3] pinned
Buffer 2: [file test, block 2] unpinned
Unpinned Buffers in LRU order: 2 0
```

위 출력의 버퍼는 해시 맵에서 검색되었기 때문에 보기에는 무작위 순서로 보일 수 있습니다. 괄호 안의 정보는 BlockId의 toString() 메서드를 호출하여 얻어온 것입니다.

코드를 디버깅하는 데 도움이 되도록 TestBufMgr.java라는 테스트 프로그램이 작성되었습니다. TestBufMgr.java는 버퍼를 pin 및 unpin하며, 때로는 버퍼 매니저의 printStatus 메서드를 호출합니다.

## Task 2: 개선된 활용을 위한 B+트리에서의 Sibling Redistribution 구현

SimpleDB의 현재 B+트리 구현은 트리의 활용을 개선하기 위한 Sibling Redistribution 기법을 포함하고 있지 않습니다. Sibling Redistribution은 언더유틸라이즈 노드가 오버유틸라이즈 노드로부터 키를 빌리거나 빌려주는 것을 허용하는 기법입니다. 이렇게 하면 스플릿(split) 또는 병합(merge) 작업을 수행하지 않고도 노드 분할과 병합의 횟수를 줄일 수 있으며, B+트리의 성능을 향상시킬 수 있습니다.

당신의 임무는 주어진 B+트리 코드를 수정하여 sibling redistribution을 구현하는 것입니다. 이를 위해 두 개의 썩지 않은 노드가 최소 두 개 썩이만큼의 활용도를 가질 수 있도록 키를 빌리거나 빌려줄 수 있도록 해야 합니다. 또한, 수정된 B+트리 구현의 성능을 측정하기 위해 여러 데이터셋과 작업(예: 삽입, 삭제, 검색, 범위 쿼리)을 사용하여 원래의 sibling redistribution이 없는 구현과 비교하는 일련의 실험을 실행해야 합니다. sibling redistribution 기법에 의해 달성된 성능 향상을 보고하고, 접근 방식의 트레이드오프 또는 제한 사항에 대해 논의해야 합니다.

## Task 3: 추가적인 관계 대수 연산자 구현


이 작업에서는 SimpleDB를 확장하여 두 가지 추가적인 관계 대수 연산자인 union과 rename을 구현해야 합니다.

rename 연산자는 세 개의 인수를 가져옵니다: 테이블을 나타내는 스캔, 테이블의 필드 이름 및 해당 필드의 새 이름. 이 연산자는 지정된 필드의 이름을 변경한 것을 제외하고 입력 테이블과 동일한 출력 테이블을 생성합니다.

반면에 union 연산자는 두 개의 스캔을 입력으로 받고, 두 스캔이 동일한 스키마를 갖습니다. 이 연산자는 중복을 포함하여 레코드의 합집합을 출력합니다.

rename 연산자는 다양한 용도로 사용될 수 있습니다. 예를 들어, 필드 값을 수정하여 이해하기 쉽게 만드는 데 사용할 수 있습니다. 다음 쿼리는 각 학생의 이름과 전공을 계산합니다. rename 연산자를 사용하여 DName 필드 이름을 Major로 변경하여 출력 테이블의 스키마가 [SName, Major]가 되도록 합니다.

rename 연산자의 또 다른 일반적인 사용법은 다른 이름을 가진 다른 필드와 결합할 수 있도록 필드 이름을 변경하는 것입니다. 다음 쿼리는 학생 #1과 동일한 전공을 가진 학생을 찾습니다.

rename 연산자의 세 번째 사용법은 하나의 스키마의 필드 이름을 다른 스키마와 일치하도록 변경하여 union 연산자를 적용할 수 있도록 하는 것입니다. 다음 쿼리는 데이터베이스의 모든 교수 및 학생의 이름을 검색합니다. 출력 테이블에는 PersonName이라는 하나의 필드가 있습니다.

당신의 임무는 rename 및 union 연산자 각각에 대한 Scan 클래스를 작성하는 것입니다. 최종 코드를 테스트하기 위해 Test3 클래스를 사용할 수 있습니다. 참고로, Test3를 실행하면 다음 출력이 생성됩니다.

"Here are the records that have the same B-value as record 33:
13 33 53 73 93 113 133 153 173 193 213 233 253 273 293
Here are the records that have the B-value 'b1' or 'b9':
1 21 41 61 81 101 121 141 161 181 201 221 241 261 281 9 29 49 69 89
109 129 149 169 189 209 229 249 269 289"

## How to Submit
소스 코드 파일을 swin.skku.edu에 제출하려면 다음과 같이 `db_submit` 명령어를 사용하십시오.

```
$ db_submit term2 /your/code/directory/path
```

이 명령은 지정된 디렉토리에 있는 모든 파일을 압축하여 제출합니다. 예를 들어, 코드 파일이 현재 디렉토리에 있다면 다음 명령을 실행하십시오.

```
$ db_submit term2 ./
```

여러 번 제출할 수 있지만, 최종 제출만 평가됩니다. 다음 명령을 사용하여 파일이 올바르게 제출되었는지 확인할 수 있습니다.

```
$ db_check_submission term2
```

이 명령은 제출 상태를 확인합니다.

## 지각 제출 정책
24시간마다 20%가 감점됩니다.

##  Q&A Piazza
질문이 있으면 Piazza에 게시하여 질문과 답변을 다른 학생들과 조교들과 공유할 수 있도록 해주세요. 문제나 질문이 있으면 자유롭게 게시해주세요. 또한, 다른 학생들의 질문에 답변할 수 있는 경우에도 자유롭게 답변해주세요.