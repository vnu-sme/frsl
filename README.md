# Functional Requirements Specification Language (FRSL)
# current version: 1.0.0

**Prerequisites:**
- Install [Eclipse DSL Tools 2024-03](https://www.eclipse.org/downloads/packages/release/2024-03/r/eclipse-ide-java-and-dsl-developers).
- Install UML2 Extender SDK (by Update Site: https://download.eclipse.org/releases/2024-03). 
- Clone this repo.
- Open repo: 
  - *File -> Open Project From File System -> Specify to folder 'frsl' -> Deselect the 'frsl' checkbox -> Select 'Search for nested projects' -> Finish*.
- In *Problem* section (in Eclipse):
  - If any project is missing *src-gen*, add manually folder *src-gen* to that project.
  - Fix the error 'An API baseline has not been set for the current workspace": (1) Click the menu: Windows\Preferences\Plug-in Development\API Baselines; (2) Update the option "Missing API baseline": Error -> Warning
  - ...
   
**Usage\:**

- Run runtime: *Right click to any project -> Run As -> Eclipse Application*.
- In runtime environment, create general project: *File -> New Project -> General -> Project -> Next ...*
- In general project, create .frsl file: *Right click -> New -> File -> Set name "test.frsl" -> Finish*
- Generate the *.frslas* file from the *.frsl* file:
  - Open the *.frsl* file -> Right click on white space in file -> FRSL -> Save As -> FRSLAS.

**Notes:**

- To integrate current frsl model2model & model2text plugins, you need to ATL & Acceleo & UML2 & Sirius by the update site: https://download.eclipse.org/releases/2024-03
- JavaSE-21 is recommended for current frsl plugins

