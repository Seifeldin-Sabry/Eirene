```plantuml
hide circle
skinparam classAttributeIconSize 0
' skinparam linetype polyline
' skinparam linetype ortho
' skinparam Handwritten true
hide empty methods
hide empty fields
left to right direction
' skinparam groupInheritance 2

package model {
package Session {
	class "Data::Reading" as sessionReading
	Session "1" o--> "0..*" sessionReading
	Session ..> "-type" SessionType
}
package Data {
	Reading --> BrainWave
	Reading --> SensorData
}
package User {
	class "Session::Session" as userSession
	User "1" --> "-currentSession 0..1" userSession
	User ..> Gender
	User "1" --> "- sessionHistory 0..*" userSession
}

class User {
	- id: long
	- name: String
	- email: String
	- password: String
}
class Session {
	- id: long
	- startTime: Timestamp
	- endTime: Timestamp
	- /duration: Timestamp { endTime - startTime }
}
class Reading {
	- readTime: Timestamp
}
class BrainWave <<dataType>> {
	- signal: int
	- level: int
}
class SensorData <<dataType>> {
	- heartRate: int
	- temperature: float
	- light: int
	- sound: int
	- humidity: float
}
enum SessionType <<enumeration>> {
	FOCUS
	MEDITATION
}
enum Gender <<enumeration>> {
	MALE
	FEMALE
	OTHER
}
}

package controller{
    interface "service::SessionService" as sessionService
    interface "service::CookieService" as cookieService
    interface "service::UserService" as userService
    interface "service:PaginationService" as paginationService
    interface "service::ReportGeneratorService" as reportGeneratorService

    SessionController --> sessionService
    SessionController --> cookieService
    SessionController --> paginationService
    UserController --> userService
    UserController --> cookieService
    UserController --> sessionService
    ActiveSessionController --> sessionService
    ActiveSessionController --> cookieService
    ActiveSessionController --> reportGeneratorService
    ActiveSessionController --> userService
    DataSenderLoginController --> userService
    SignUpController --> userService
    SignUpController --> cookieService
    LoginController --> userService
    LoginController --> cookieService
    class MainController
    class GlobalErrorHandlingController
}

package presenter{
    class PasswordEditViewModel implements PasswordMatcher
    interface PasswordMatcher
    class SessionEditViewModel
    class SessionFeedbackViewModel
    class UserEditViewModel
    class UserLoginViewModel
    class UserSignUpViewModel implements PasswordMatcher
}

package service {
    package evaluator {
        interface EvaluatorService
        interface ReportGeneratorService
        class EvaluatorServiceImpl implements EvaluatorService
        class ReportGeneratorServiceImpl implements ReportGeneratorService
    }
    interface SessionService
    interface UserService
    interface CookieService
    interface GlobalAnalyticsComparator
    interface PaginationService
    class CookieServiceImpl implements CookieService
    class GlobalAnalyticsComparatorImpl implements GlobalAnalyticsComparator
    class SessionPaginationService implements PaginationService
    class SessionServiceImpl implements SessionService
    class UserServiceImpl implements UserService
    interface "repository::SessionRepository" as sessionRepository
    interface "repository::UserRepository" as userRepository
    SessionServiceImpl "1" --> "1 sessionRepository" sessionRepository
    UserServiceImpl "1" --> "1 userRepository" userRepository

}

package repository {
    interface SessionRepository
    interface UserRepository
    interface "CrudRepository<User, Long>" as userCrud
    interface "CrudRepository<Session, Long>" as sessionCrud
    SessionRepository --|> sessionCrud
    UserRepository --|> userCrud
}


class ActiveSessionController

class SessionController

class LoginController

class UserController

class DataSenderLoginController

class SignUpController

'interface "CrudRepository<Session, Long>" as sessionCrud

'interface "CrudRepository<User, Long>" as userCrud
```