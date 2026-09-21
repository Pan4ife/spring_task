Spring Framework — 단계별 학습 과제

Habsida 교육 과정의 Spring Framework 과제를 브랜치별로 정리한 저장소입니다. 빈 등록과 스코프에서 시작해 의존성 주입, Spring + Hibernate 통합까지 단계적으로 진행했습니다.
설명
기본 브랜치 `task_1_1_2`는 과제 템플릿이며, 실제 작업 내용은 아래 브랜치에 있습니다.
브랜치별 내용
브랜치	기간	학습 주제	구현 내용
`task_2_1_3`	2026-04-14	빈 등록과 스코프	`@Configuration` / `@Bean`으로 빈 등록, singleton과 prototype 스코프의 동작 비교 (동일 객체 여부 확인)
`task_2_1_4`	2026-04-15	컴포넌트 스캔과 빈 선택	`@Component`, `@Scope`, `@Autowired`, `@Qualifier`로 주입할 빈 지정
`task_2_1_5`	2026-04-16	빈 간 의존성 연결	생성자·세터·필드 주입과 `@Bean` 메서드 파라미터 주입으로 빈 체인 구성
`task_2_2_1`	2026-04-22 ~ 04-23	Spring + Hibernate 통합	아래 참고
task_2_2_1 — Spring + Hibernate (사용자·차량)
Spring 컨테이너가 관리하는 Hibernate 세션과 트랜잭션 위에서 `User`와 `Car`를 저장·조회합니다.
스택
Spring Framework 5 — Core, ORM, Transaction
Hibernate 5 — `LocalSessionFactoryBean`, `HibernateTransactionManager`
MySQL 8 — 데이터베이스
Maven — 빌드
Java
아키텍처
```
Service (@Service @Transactional)   ← 비즈니스 로직, 트랜잭션
        ↓
DAO (@Repository)                   ← SessionFactory 기반
        ↓
Entity (User ↔ Car, @OneToOne)
        ↓
MySQL
```
구현 내용
`Car` 엔티티 작성, `User`–`Car` 1:1 연관관계 매핑 (`@OneToOne`, `@JoinColumn`, `cascade = ALL`)
HQL 파라미터 바인딩으로 차량 모델·시리즈 기준 사용자 검색 (`searchUserByCar`) — DAO와 Service 계층에 구현, `@Transactional` 적용
기존 Spring 설정(`AppConfig`: `DataSource`, `LocalSessionFactoryBean`, `HibernateTransactionManager`)에 엔티티를 등록하여 동작 확인
실행
MySQL 8을 설치하고 데이터베이스를 생성합니다.
```sql
   CREATE DATABASE spring_hiber;
```
`src/main/resources/db.properties`에 본인의 DB 계정 정보를 입력합니다.
`MainApp`을 실행합니다. 사용자 4명(차량 포함)을 저장한 뒤 전체 목록과 차량 조건 검색 결과를 출력합니다.
