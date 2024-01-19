# Functional Requirements Specification Language (FRSL) - frsl-1.0.0

## Prerequisites

### IDE Settings

- Install [Eclipse DSL Tools (Version: 2023-12)](https://www.eclipse.org/downloads/packages/release/2023-12/r/eclipse-ide-java-and-dsl-developers).
- JavaSE version: 17 <= V <= 19. The JavaSE comes with the IDE should be enough, although Eclipse Temurin or OpenJDK are recommended, too.
- Create a workspace to work with the repo.
- Download plugins:
  - `Help -> Install New Software... -> Work with: [fill in] Eclipse Repository - https://download.eclipse.org/releases/2023-12`
  - Search and select: ATL (ATL + ATL SDK; both v4.8.0); Acceleo (v3.7.14); UML2 (UML2 Extender SDK and UML2 Extender SDK Developer Resources; both v5.5.3).

### Workspace Settings

- Clone this repo.
- Import projects into the Eclipse workspace:
  - `File -> Open Project From File System -> Specify to folder 'frsl-1.0.0' -> Deselect the 'frsl-1.0.0' checkbox -> Select 'Search for nested projects' -> Finish`.
- In the *Problem* section:
  - If any project is missing *src-gen*, add manually folder *src-gen* to that project.
  - Fix the error "An API baseline has not been set for the current workspace":
    - Click the menu: `Windows\Preferences\Plug-in Development\API Baselines`
    - Update the option "Missing API baseline": Error -> Warning.
  - ... TO BE UPDATED ...

## Usage

- Run a runtime environment: `Right click to any project -> Run As -> Eclipse Application`.
- In runtime environment, create a general project: `File -> New Project -> General -> Project -> Next ...`.
- In the general project, create a `.frsl` file: `Right click -> New -> File -> Set name "test.frsl" -> Finish`
- Generate the `.frslas` file from the `.frsl` file:
  - `Open the .frsl file -> Right click on white space in file -> FRSL -> Save As -> FRSLAS`.

## Notes

- The current version (19/01/2024) should work without IDE-breaking-bugs with these guidelines.
- ... TO BE UPDATED ...
