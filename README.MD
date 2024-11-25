# TEENYTINYURL - A UNIQUE URL GENERATOR

`TEENYTINYURL` is a simple, lightweight URL shortener designed to generate unique short URLs for given long URLs. This project was created as a proof of concept for learning purposes, using Spring Boot to power the backend.

- **Generate Short URL**: Converts a given long URL into a unique, shortened URL.
- **Fetch Long URL**: Retrieves the original long URL from the generated short URL.

## API Endpoints

- **Endpoint**: `POST /api/shorten`
- **Request Body**:

  ```json
  {
    "longURL": "string"
  }
  ```

- **Response**:

  ```json
  {
    "longURL": "string",
    "shortUrl": "string"
  }
  ```

- **Sample Request/Response**:

  ```bash
  curl -X POST "http://localhost:8080/api/shorten" -H "Content-Type: application/json" -d "{\"longURL\": \"https://bing.com\"}"
  {"shortUrl":"Be3f7a","longURL":"https://bing.com"}
  ```

---

- **Endpoint**: `GET /api/fetch/{shortURL}`

- **Response**:

  ```json
  {
    "shortUrl": "string",
    "longURL": "string"
  }
  ```

  - **Sample Request/Response**:

  ```bash
  curl -X GET "http://localhost:8080/ABC" -I
  HTTP/1.1 307
  Location: https://test.com
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Sun, 24 Nov 2024 07:08:22 GMT
  ```

> If the short URL is found, the response will be an HTTP 302 status for temporary redirection, with the original long URL included in the Location header.

## THOUGHT PROCESS

### Number of Unique URLs the App Should Support: 1 Billion (10^9)

- **Allowed Characters**: All alphanumeric characters (62 characters: A-Z, a-z, 0-9)

- **Size of Short URL**:

  A short URL can be represented as a string with `n` positions, each of which can be one of the 62 alphanumeric characters.

  - Each position has 62 possible characters.
  - To calculate the minimum length `n` for the short URL, we need:

    \[
    62^n > 10^9
    \]

  Solving for `n`, we find that **n = 6**.

### How to Generate Unique Short URLs?

1. **Generate a Hash**:

   - Use a hashing algorithm like SHA-256 to generate a hash of the long URL.
   - Extract the first 6 characters from the hash.

2. **Handle Hash Collisions**:
   - There may be collisions (i.e., the same short URL generated for different long URLs).
   - If a collision occurs, check if the generated short URL already exists in the database.
   - If it does, append a predefined character (e.g., a random character or sequence) to the short URL and repeat the process.

## OTHER CONSIDERATIONS

- **Handling Very Long URLs**:

  - If the long URL exceeds the database's storage capacity, we can enforce a client-side and server-side limit on the number of characters allowed.
  - It's crucial to have a server-side limit in place, as the client-side limit can easily be bypassed using proxies or other techniques.

- **Handling More Than 1 Billion Requests**:

  - If the system reaches more than 1 billion requests, we can consider discarding excess requests.
  - To manage data retention, we can use a **TTL (Time To Live)** feature to automatically remove records that are too old, ensuring the database remains manageable.

- **Handling Excessive Requests from a Single IP**:
  - If a single IP address generates too many records or there is a spike in traffic, we can implement a **rate-limiter** service.
  - The rate-limiter can help mitigate abuse and ensure fair use of the service by limiting the number of requests a user can make within a certain time period.
