# Football Fantasy

Microservice Football Fantasy application built with Spring Boot framework for the needs of the master's thesis ["Comparative analysis of monolithic and microservice applications characteristics in Java EE environment"](https://github.com/deniskuzner/masters-thesis). It manages users, teams, players, clubs, leagues, matches and statistics. System supports two types of user roles: USER and ADMIN.

After registration and logging in to the system, user can create a team. User team is created by selecting fifteen players within a limited budget where the team must not contain more than three players of the same club. After that, user can change selected team by making substitutions and transfers. User gets weekly points based on the actual performances of selected players in real-world matches. He can also view matches, results and create a new league or join an existing one in which he can compete with his friends.

On the other hand, administrator is responsible for updating or deleting players, clubs and teams.

The following microservices were created as a result of incremental decomposition of the monolithic Football Fantasy application by applying decomposition strategies by business capability and domain object:
+ `` football-fantasy-parser-service `` - function of parsing data about matches, teams and players from external sources
+ `` football-fantasy-stats-service `` - function of statistics and points calculation
+ `` football-fantasy-team-service `` - managing domain object Team
+ `` football-fantasy-user-service `` - managing domain object User
+ `` football-fantasy-service `` - the rest of the monolithic application for which further decomposition did not prove justified because it contains frequently used functions by other microservices to which they do not belong logically
+ `` football-fantasy-gateway `` - function of receiving and routing client requests

The following figure shows the system architecture.

![img1](https://i.ibb.co/9VWTBHY/mikroservisna-aplikacija.jpg)
