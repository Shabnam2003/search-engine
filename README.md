
# Search Engine Project

This project simulates a simple search engine using the map data structure. The program constructs an inverted index from a set of documents, enabling efficient searching. The search engine can process different types of user queries and display the names of documents that match the search criteria.

## Features

- **Inverted Index Construction**: The program reads multiple documents and constructs an inverted index, mapping each word to the documents in which it appears.
- **Search Queries**: The program supports three types of queries:
  1. **Must-have Words**: Words that must appear in the search results (no prefix).
  2. **At Least One Word**: Words prefixed with `+`, where at least one of the specified words must be present in the result.
  3. **Exclusion Words**: Words prefixed with `-`, which should not be present in the search results.

## How It Works

1. **Inverted Index**: The inverted index is created by reading documents and storing the words along with their corresponding document names.
2. **User Input**: The program takes a search query from the user and returns the document names based on the following rules:
   - **No Prefix**: Returns documents containing all the specified words.
   - **+ Prefix**: Returns documents containing at least one of the specified words.
   - **- Prefix**: Excludes documents containing the specified word.

## Example

### Query 1: No Prefix (Must-have Words)
```
Input: "apple orange"
Output: Documents containing both "apple" and "orange".
```

### Query 2: + Prefix (At Least One Word)
```
Input: "+apple +banana"
Output: Documents containing either "apple" or "banana" or both.
```

### Query 3: - Prefix (Exclusion)
```
Input: "-apple"
Output: Documents that do not contain the word "apple".
```
