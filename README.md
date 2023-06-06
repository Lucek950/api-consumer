# Spring GitHub Repository API
This is a Spring-based API application that allows retrieving information about GitHub repositories. The application communicates with the GitHub API interface to fetch repository details for a specific user.

## Acceptance Criteria
The acceptance criteria for this project are as follows:

1. As an API user, when providing a username and the header "Accept: application/json", I want to receive a list of non-fork repositories on GitHub for that user. The response should include the following information:

* Repository name
* Owner login
* For each branch, the branch name and the SHA of the latest commit.
2. As an API user, when providing a non-existent GitHub user, I expect to receive a 404 response in the following format:

```shell
{
  "status": 404,
  "message": "User not found"
}
```

## API Endpoints
The following API endpoints are available:

1. GET /repositories/{username} 

   Description: Fetches GitHub repositories for the specified user.

   Parameters:

   * {username} (path parameter): The GitHub username for which repositories should be retrieved.

   Response:

* 200 (OK) status code with the following JSON structure:
```shell
[
  {
    "repository_name": "repository-name",
    "owner_login": "owner-login",
    "branches": [
          {
            "branch_name": "branch-name",
            "commit_sha": "latest-commit-sha"
          },
          {
            "branch_name": "feature-branch",
            "commit_sha": "e5f6g7h8"
          }
    ]},
  ...
]
```
## Error Handling
The application handles errors in the following way:

* If an error occurs during communication with the GitHub API interface, the application will display the processed message from GitHub.
* If a non-existent GitHub user is provided, a 404 response code will be returned along with an appropriate error message.

## Testing
Tests have been performed to ensure the correctness and functionality of the application.

## Development Environment Setup
To set up the project for local development, follow these steps:

1. Clone the repository from GitHub.
2. Make sure you have Java and Maven installed on your system.
3. Navigate to the project's root directory.
4. Build the project using the following command:
```shell
mvn clean install
```
5. Run the application using the following command:
```shell
mvn spring-boot:run
```
The application will be available at http://localhost:8080.

PS About Me:
Personally, I feel very weak in conversing in English. I understand documentation, but I cannot have fluent conversations. I apologize for the delivery time of the solution. I struggled with Swagger and wanted to write tests in Groovy, but unfortunately, I had difficulties with that, and nothing came out of it. Although I know that I don't qualify due to the English requirement, I really wanted to deliver the task and perhaps receive feedback.