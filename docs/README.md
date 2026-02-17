# BMO User Guide

BMO is a chatbot that helps you manage todos, deadlines, and events from the command line or GUI.

## Features and Usage

Use the commands below in the input box (GUI) or terminal input (CLI).

- Action: Show all tasks
	- Command format: `list`
	- Example: `list`

- Action: Add a todo task
	- Command format: `todo <description>`
	- Example: `todo read book`

- Action: Add a deadline task
	- Command format: `deadline <description> /by <yyyy-MM-dd HHmm>`
	- Example: `deadline submit report /by 2026-03-01 2359`

- Action: Add an event task
	- Command format: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
	- Example: `event project meeting /from 2026-02-18 1400 /to 2026-02-18 1530`

- Action: Mark a task as done
	- Command format: `mark <task number>`
	- Example: `mark 2`

- Action: Mark a task as not done
	- Command format: `unmark <task number>`
	- Example: `unmark 2`

- Action: Delete a task
	- Command format: `delete <task number>`
	- Example: `delete 3`

- Action: Find tasks by keyword
	- Command format: `find <keyword>`
	- Example: `find meeting`

- Action: Show help
	- Command format: `help`
	- Example: `help`

- Action: Exit the chatbot
	- Command format: `bye`
	- Example: `bye`

## Error Handling

- Invalid commands are handled gracefully with a clear error message.
- Invalid task indices (for example, `mark 0` or non-numeric input) are rejected with guidance.
- If the data file is missing, BMO starts with an empty task list instead of crashing.

## Acknowledgements

- Project structure adapted from the CS2103T iP Java chatbot template.
- Some implementation refinements were AI-assisted using GitHub Copilot (GPT-5.3-Codex), with AI-assisted notes in edited code regions.