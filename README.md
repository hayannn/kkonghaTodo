# KKonghaTodo
간단한 투두리스트 프로젝트
- SpringBoot를 이용한 백엔드 구현
- SpringBoot Starter thymeleaf를 이용한 간단 화면 구현
<img width="1624" alt="image" src="https://github.com/hayannn/kkonghaTodo/assets/102213509/051469ef-3469-4297-b36c-1a9497d4861a">

![KakaoTalk_20240228_201521751](https://github.com/hayannn/kkonghaTodo/assets/102213509/346caff1-9696-4970-989b-f356eed8d0d9)


<br>
<br>

### 사용 기술스택
> Backend <br>
1. SpringBoot
2. MySQL

<br>

>Frontend <br>
- Bootstrap과 스프링부트 내 thymeleaf 라이브러리 사용해 간단히 화면 구현

<br>

>OS <br>
1. Windows 11
2. MacOS Sonoma 14

<br>
<br>

### 라이브러리
1. lombok
2. MySQL Connector
3. spring data jpa
4. spring thymeleaf
5. spring web
6. spring boot test



<br>
<br>



### 프로그램 구조
```
└── 🗂 main
    ├── 🗂 java
    │   └── 🗂 com
    │       └── 🗂 kkongha
    │           └── 🗂 todo
    │               ├── 📑 TodoApplication.java
    │               │  
    │               ├── 🗂 config
    │               │   └── 📑 MvcConfiguration.java
    │               │ 
    │               ├── 🗂 controller
    │               │   └── 📑 TodoController.java
    │               │    
    │               ├── 🗂 model
    │               │   ├── 📑 AddTodo.java
    │               │   ├── 📑 DueDateFilter.java
    │               │   └── 📑 TodoEntity.java
    │               │
    │               ├── 🗂 repository
    │               │   └── 📑 TodoRepository.java
    │               │
    │               ├── 🗂 service
    │               │   ├── 📑 TodoService.java
    │               └── └── 📑 TodoServiceImpl.java
    └── 🗂 resources
        │   ├── 🗂 static
        │   └── 🗂 templates
        │       ├── 🖥️ add-todo.html
        │       ├── 🖥️ edit-todo.html 
        │       └── 🖥️ todo-list.html 
        │     
        └── 📑 application.yml      
```

<br>
<br>

### API Info
| 기능                         | HTTP 메서드 | API Endpoint                      | 설명                  |
  |---------------------------------|-------------|---------------------|-----------------------------------|
| 모든 할 일 목록 조회           | GET         | /todos                          | 모든 투두 리스트를 가져온다.    |
| 특정 할 일 조회               | GET         | /todos/{id}                     | 특정 투두 리스트를 가져온다.    |
| 할 일 추가                   | POST        | /todos                          | 새로운 투두를 추가한다.       |
| 할 일 수정                   | PUT         | /todos/{id}                     | 특정 투두를 수정한다.        |
| 할 일 삭제                   | DELETE      | /todos/{id}                     | 특정 투두를 삭제한다.        |
| 할 일 체크(완료 여부)         | GET         | /todos/toggle-completion/{id}   | 완료한 투두를 체크박스에 체크한다. |

<br>
<br>


### 현재 진행 상황(Last Update: 2024.04.04)

https://github.com/hayannn/kkonghaTodo/assets/102213509/3f2b08a6-c332-4809-aacc-4206ba81c92a

- 투두리스트 CRUD 완성

- 메인 화면 기본 적용 및 디자인 수정 완료
  - todo-list.html


- 투두 추가, 투두 수정 화면 기본 적용 및 디자인 수정 완료
  - add-todo.html
  - edit-todo.html
     <details>
      <summary><strong>4번 관련 화면</strong></summary>
    
      ![스크린샷 2024-02-28 195644](https://github.com/hayannn/kkonghaTodo/assets/102213509/fa0f6570-6f40-40fa-a22c-39cbc09156db)
      ![스크린샷 2024-02-28 195702](https://github.com/hayannn/kkonghaTodo/assets/102213509/f91ba9b1-4105-4aa8-86ba-009b7830eb1a)

      </details>
  
  
- 날짜 필터링 수정 완료
  - 리다이렉트 시 날짜 정보 초기화되는 현상 발생 => 날짜 선택 시 날짜 값 유지
      <details>
      <summary><strong>동영상</strong></summary>
    
       https://github.com/hayannn/kkonghaTodo/assets/102213509/9f9be7a8-5b9d-4301-95b3-999fadc91264

      </details>
  
  
- DB 오류 수정 완료
  - CreatedAt 및 UpdateAt이 NULL로 적용되는 현상 발생 => TIMESTAMP 적용 및 String 형태에서 Date 형태로 수정 
  - TodoEntity.java


- 투두 성공률 계산
  - SuccessRate를 추가해 총 투두 성공률 계산 및 보여주기 기능 구현중
    - 0 ~ 30% : 🔴
    - 31 ~ 70% : 🟡
    - 71 ~ 100% : 🟢


<br>
<br>

### ♻️ 해결 중인 문제
> [프로그레스 바](https://getbootstrap.kr/docs/5.0/components/progress/)

- 백엔드 로직 개발 및 프론트 적용 완료했으나 반응 없는 문제 발생
  - 🚀 Backend 코드에서 SuccessRate 관련 로직 수정 진행중

    <details>
    <summary><strong>📂 service/TodoServiceImpl.java </strong></summary>
  
      ```java
      // 성공률 계산 추가
      @Override
      public void calculateSuccessRate(List<TodoEntity> todos) {
          for (TodoEntity todo : todos) {
              int totalTodos = todos.size();
              int completedTodos = 0;
              for (TodoEntity t : todos) {
                  if (t.isCompleted()) {
                      completedTodos++;
                  }
              }
              double successRate = (double) completedTodos / totalTodos * 100;
              todo.setSuccessRate(successRate);
          }
      }
      ```

    </details>
  
  <br>

> 리다이렉트 로직 수정
- 모든 변경점 발생 시 기본 페이지(/todos)로 리다이렉트되는 문제 발생
  - ```원하는 결과 : 수정을 마치고 그 이전 페이지(대신 수정사항이 반영된 상태로)로 리다이렉트```
    - 🚀 Backend 코드의 Controller 수정 작업 진행중
      <details>
        <summary><strong>📂 controller/TodoController.java </strong></summary>
  
        ```java
        ...
    
        @GetMapping("/filter")
          public String filterTodos(@RequestParam(name = "dueDate", required = false) String dueDateString, Model model) {
              List<TodoEntity> todos;
              if (dueDateString != null) {
                  try {
                      Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                      todos = todoService.getTodosByDueDate(dueDate);
                      model.addAttribute("currentDate", dueDateString);
                  } catch (ParseException e) {
                      todos = todoService.getAllTodos();
                  }
              } else {
                  todos = todoService.getAllTodos();
              }
              model.addAttribute("todos", todos);
              return "todo-list";
          }
      
        ...
      
        @GetMapping("/today")
        public String getTodayTodos(Model model) {
          List<TodoEntity> todayTodos = todoService.getTodayTodos();
          model.addAttribute("todos", todayTodos);
          return "todo-list";
        }
        ```
      </details>
  
<br>
<br>


### 예정 작업
> 성공률 계산
  - 총 투두 성공률 계산 및 보여주기(0 ~ 30% : 빨간색, 31 ~ 70% : 노란색, 71 ~ 100% : 초록색)

<br>

> Swagger 및 queryDSL 적용
  - API 테스트를 Postman에서 ➡️ Swagger로 자동화하기
  - queryDSL을 통해 JPA와 다른 퀴리 작성 방법 연구하기
    - [Spring Data JPA + QueryDSL 적용](https://medium.com/mo-zza/spring-data-jpa-querydsl-%EC%A0%81%EC%9A%A9-22a0364cd579)
    - [[SpringBoot] QueryDSL JPA 사용 이유 생각해보기](https://velog.io/@jmjmjmz732002/SpringBoot-QueryDSL-JPA-1-%EC%82%AC%EC%9A%A9-%EC%9D%B4%EC%9C%A0-%EC%83%9D%EA%B0%81%ED%95%B4%EB%B3%B4%EA%B8%B0)])

<br>

> 모든 상위 작업 이후 기능 요소 추가
- 각 투두별 시간 지정
- 월별로 루틴 성공률 색깔 모아서 볼 수 있도록 하기
- 데일리 회고 기능 추가



<br>



