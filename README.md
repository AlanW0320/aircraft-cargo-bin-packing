# Aircraft Cargo Bin Packing

Java implementation of classical bin-packing algorithms (Next Fit, First Fit Decreasing, Best Fit Decreasing) applied to aircraft cargo loading. This project was developed as part of the UECS2453 Problem Solving with Data Structures and Algorithms assignment and provides a small, clear codebase to experiment with bin-packing strategies on sample datasets.

## Table of contents
- [Features](#features)
- [Algorithms implemented](#algorithms-implemented)
- [Project structure](#project-structure)
- [Requirements](#requirements)
- [Build & run](#build--run)
- [Example usage](#example-usage)
- [Output](#output)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Features
- Simple Java implementation of multiple bin-packing algorithms.
- Loader for dataset text files and exporter for packing solutions.
- Example datasets included (small/large/edge cases).
- Outputs packing solutions in a human-readable format.

## Algorithms implemented
- NFAlgorithm (Next Fit) — places each item into the most recently opened bin; opens a new bin if it doesn't fit.
- FFDAlgorithm (First Fit Decreasing) — sorts items in decreasing size and places each into the first bin where it fits.
- BFDAlgorithm (Best Fit Decreasing) — sorts items in decreasing size and places each into the bin that leaves the least leftover space (best fit).

## Project structure
Top-level important files and folders:

```
src/
  main/
    Main.java                # Program entry point
  algorithms/
    NFAlgorithm.java         # Next Fit
    FFDAlgorithm.java        # First Fit Decreasing
    BFDAlgorithm.java        # Best Fit Decreasing
  core/
    Bin.java                 # Bin model (capacity, items)
    Item.java                # Item model (id, size/weight)
    PackingSolution.java     # Solution representation
  datasets/
    DataFileLoader.java      # Load dataset files into Item objects
  exporter/
    BinSolution.java         # Exporter/formatter for solutions
module-info.java             # Java module descriptor (small)
data/
  SmallDataset.txt
  LargeDataset.txt
  EdgeDataset.txt
BinSolutionOutput.txt        # Example output included
bin/                         # compiled classes (committed)
```

How it fits together:
- The Main class ties the pieces: it loads item lists using DataFileLoader, runs a selected algorithm (NF/FFD/BFD) from src/algorithms, assembles a PackingSolution using core classes (Item, Bin, PackingSolution), and uses the exporter to write a formatted solution.

## Requirements
- Java 11+ (any modern JDK should work)
- No external build tools required (this is a plain Java project). The repository includes compiled classes under `bin/`, but you can recompile from `src/`.

## Build & run
Compile all sources into a `bin` directory and run the Main class.

POSIX / macOS / Linux (bash):

```bash
# from repository root
mkdir -p bin
find src -name "*.java" > sources.txt
javac -d bin @sources.txt
# run (Main is under package 'main' based on src layout)
java -cp bin main.Main data/SmallDataset.txt
```

Windows (PowerShell):

```powershell
# from repository root
mkdir bin
Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName } > sources.txt
javac -d bin @sources.txt
java -cp bin main.Main data\SmallDataset.txt
```

Notes:
- If your JDK or environment uses modules and Main is packaged as a module, you may prefer `java --module-path` / `java -m` invocation. The simple classpath approach above works when the `main` package/class is present on the classpath.
- If you prefer an IDE, import the project as a standard Java project (Eclipse/IntelliJ) and run Main from the IDE.

## Example usage
- Provided datasets:
  - `data/SmallDataset.txt` — small test set
  - `data/LargeDataset.txt` — larger test set
  - `data/EdgeDataset.txt` — corner-case items

Run the program with a dataset file as argument (example):

```bash
java -cp bin main.Main data/LargeDataset.txt
```

After running, check `BinSolutionOutput.txt` or the console output for the packing result (the repository includes `BinSolutionOutput.txt` as a sample).

## Output
The exporter produces a human-readable packing solution describing bins, assigned items, and used capacity. See `BinSolutionOutput.txt` in the repository for an example output layout.


## License
No license file is included in the repository. If you intend to share or reuse this project, add a LICENSE file (MIT, Apache-2.0, or other) to clarify reuse terms.
