# Java Programs

## Modules

#### Basic Programs (java-basic-programs)

#### Collection Programs (java-collection-stream-programs)

#### Hacker Rank Solution Programs (java-hacker-rank-solution-programs)

#### Lombok Programs (java-lombok-programs)

#### MapStruct Programs (java-mapstruct-programs)

***

## Installation

The project requires the following software and plugins to be installed.

#### Software

- Java 8 and above
- Maven
- Git

#### Plugin

- Lombok
- google-java-format

***

## Contribute to the project

The main branch is reserved for the admin and can be updated only after a merge request is raised and approved to add
changes. To contribute to the project, please follow the steps below.

- Clone the project to your local system
- Create a new branch off the main branch or switch to an existing branch

```
git clone git@github.com:teb-java-programming/java-programs.git
git checkout -b <newBranch>
git checkout <main> <existingBranch>
```

- Check the files in the project that have been added or updated

```
git status --> shows the list of files
git diff --> shows the changes to an existing file
git add . --> stages all changes to be committed onto the sub-branch
git commit -m <commitDetails> --> commits the changes, ready to be pushed to remote
git push --> changes are added to remote, ready to be reviewed
```

***

## Self-Review Code Before Commit

The code should be reviewed and a successful maven build should be run to confirm the desired output.

In order to make it look visibly appealing and easier to follow, use the google-java-format plugin to align the code and
remove unused imports.

#### Shortcuts

```
Mac:
⌘ command + ⌥ option + L --> aligns the code
⌃ control + ⌥ option + O --> removes unused imports

Windows:
Ctrl + Alt + L --> aligns the code
Ctrl + Alt + O --> removes unused imports
```
