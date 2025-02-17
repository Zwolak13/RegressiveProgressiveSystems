# Project Overview 🎉

Regressive and Progressive App, a reasoning system that uses **Progressive** and **Regressive** methods to work with knowledge bases and facts. 🚀

It's a pretty cool tool if you're into **Artificial Intelligence**, **Rule-based Systems**, or just experimenting with how logic can help us infer new information! 🤖💡

---

## What's Inside? 🧐

- **Progressive Reasoning**: This part gradually adds new facts to the set, trying to build up the knowledge to reach the target goal.

- **Regressive Reasoning**: Think of this like solving a puzzle, but backward. It starts with the goal and works backward to check if the target can be achieved through known facts.

---

## How to Run It 🏃‍♂️

### Prerequisites 🔧
Make sure you have the following installed:

- **Java** (JDK 8 or later)
- **Apache POI** library (for reading `.xlsx` files) 📖

### Running the Project 🚀

1. **Launch the Application**:
   The main application file is `Launcher.java`, just run it, and you're good to go.

2. **Input Your Knowledge Base**:
   You can either provide a **TXT** or **XLSX** file as your knowledge base (rules) and fact set (the things you already know). 🧠💡

3. **Progressive or Regressive?**:
    - Progressive: It’s all about **growing** your facts as it goes along.
    - Regressive: It’s like **solving a mystery**—start with the goal and work backward. 🕵️‍♂️

---

## How It Works 🔍

### The Files 🗂️

- **`Launcher.java`**: This is where the app kicks off! It launches the main program (`ProgramApplication`).
- **`Progressive.java`**: Implements **Progressive** reasoning, adds new facts and executes the inference.
- **`ProgressiveHelper.java`**: Helps with building the set of rules and checking if facts match.
- **`Regressive.java`**: Implements **Regressive** reasoning, where we go backward to find our goal.
- **`RegressiveHelper.java`**: Supports **Regressive** reasoning by creating the queue of rules and checking if premises are true.
- **`TXTFileReader.java`**: Handles reading rules and facts from text files. 📄
- **`XLSXFileReader.java`**: Deals with reading **Excel** files—because sometimes TXT just isn’t enough! 📊
- **`IProgressive.java`**: Interface for the **Progressive** reasoning system. 🎯
- **`IRegressive.java`**: Interface for the **Regressive** reasoning system. 🔄

### What's the Deal with Knowledge Base and Fact Set? 🤔

- **Knowledge Base**: A set of rules that help the system infer new facts. It’s like the brain of the system! 🧠
- **Fact Set**: These are the things we already know. These facts are used to figure out new truths. ⚡

---

## How to Use It 💬

### The Basics:

1. **Loading the Knowledge Base**:
    - You can load your knowledge base either from a `.txt` or `.xlsx` file.

2. **Executing the Reasoning**:
    - **Progressive Reasoning**: The system will keep adding facts until it either runs out of rules or reaches the target.
    - **Regressive Reasoning**: Starts with a goal and traces backward to see if we can prove it by the facts we know.

3. **Erasing Knowledge**:
    - Whenever you need to clear the current fact set or knowledge base, you can do it easily using the provided methods.

---
## 🎩 Authors
Dawid Zwolak & Mikołaj Sosiński

