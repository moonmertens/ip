# BMO User Guide

BMO is a task chatbot that helps you keep track of todos, deadlines, and events.

## Quick Start

Supported commands:

- `list`
- `todo <description>`
- `deadline <description> /by <yyyy-MM-dd HHmm>`
- `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
- `mark <task number>`
- `unmark <task number>`
- `delete <task number>`
- `find <keyword>`
- `help`
- `bye`

## Error Handling

- Invalid commands are handled gracefully with a clear error message.
- Invalid task indices (e.g., `mark 0`, non-numeric input) are rejected with guidance.
- If the data file is missing, BMO starts with an empty list instead of crashing.

## Acknowledgements

- Project structure adapted from the CS2103T iP Java chatbot template.
- Some implementation refinements were done with AI assistance (GitHub Copilot / GPT-5.3-Codex), with in-code AI-assisted annotations where relevant.