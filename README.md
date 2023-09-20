# Functional Requirements Specification Language (FRSL)
# frsl-1.0.0
**Acronyms:**
- SMD: State Machine Diagram.
- CD: Class Diagram.
- AS2CS: Abstract Syntax To Concrete Syntax.
- TC: Test case.
- UI: User Interface.
- AD: Activity Diagram.
- UCD: Use Case Diagram.
- ...

**Prerequisites:**
- Install [Eclipse DSL Tools (Version: 2023-06)](https://www.eclipse.org/downloads/packages/release/2023-06/r/eclipse-ide-java-and-dsl-developers).
- Install [Eclipse plugin Sirius 7.2.1 (By Update Sites)](https://download.eclipse.org/sirius/updates/releases/7.2.1/).
- Install UML2
- Install [Acceleo (By Update Sites)] (https://download.eclipse.org/acceleo/updates/releases/3.7/R201911060712)
- Install [ATL (Update Sites)] ( https://download.eclipse.org/mmt/atl/updates/releases/4.8.0)
- Clone this repo.
- Open repo: 
  - *File -> Open Project From File System -> Specify to folder 'plugins' -> Deselect the 'plugins' checkbox -> finish*.
  - *File -> Open Project From File System -> Specify to folder 'examples' -> Deselect the 'examples' checkbox -> finish*.
- In *Problem* section (in Eclipse):
  - If any project is missing *src-gen*, add manually folder *src-gen* to that project.
  - Fix the error 'An API baseline has not been set for the current workspace": (1) Click the menu: Windows\Preferences\Plug-in Development\API Baselines; (2) Update the option "Missing API baseline": Error -> Warning
  - ...

## Plugin Gen-SMD-CD + Gen-AS2CS(Text)
**Prerequisites:**
- Install [Papyrus Plugin (By Update Sites)](https://download.eclipse.org/modeling/mdt/papyrus/updates/releases/2023-06): *Help -> Install New Software -> Paste link to "Work with"-> Enter -> Tick all package -> Next ...*

**Note:**

- Can gen SMD, CD from *.frsl* file OR *.frslas* file.
- Can gen *.frslas* file from *.frsl* file.

**Usage\:**

- Run runtime: *Right click to any project -> Run As -> Eclipse Application*.
- In runtime environment, create general project: *File -> New Project -> General -> Project -> Next ...*
- In general project, create .frsl file: *Right click -> New -> File -> Set name "test.frsl" -> Finish*
- Gen SMD, CD from *.frsl* file:
  - Open *.frsl* file -> Right click on white space in file -> FRSLCS -> Save As -> UML.
- Gen *.frslas* from *.frsl* file:
  - Open *.frsl* file -> Right click on white space in file -> FRSLCS -> Save As -> FRSLAS.
- Gen SMD, CD from *.frslas* file:
  - Open *.frslas* file -> Right click on file icon -> FRSLCS -> Generate Smc Uml.
- Visual UML diagram (by Papyrus Plugin):
  - Right click file icon .uml -> New -> Papyrus Model -> Finish
  - To Visual Class Diagram:
    - In 'Model Explorer' section, right click top-level meta-concept -> New Diagram -> Class Diagram
    - Right click on white space -> Filters -> Synchronized with model.
    - Ctrl+A -> Right click on white space -> Filters -> Show/Hide Contents.
  - To Visual State Machine Diagram:
    - In 'Model Explorer' section, right click 'StateMachine' meta-concept -> New Diagram -> State Machine Diagram
    - Right click on white space -> Filters -> Synchronized with model.
    - Ctrl+A -> Right click on white space -> Filters -> Show/Hide Contents.