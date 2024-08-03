# GeolocationAPITests_BDD_RestAssured
Google Geolocation API Automation using BDD in Cucumber and Rest Assured for API Tests

## Getting Started

### Prerequisites

- Java JDK 17.0.8
- Maven 3.9.6
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/NamanKhurana/GeolocationAPITests_BDD_RestAssured.git
    ```

2. Navigate to the project directory:

    ```bash
    cd GeolocationAPITests_BDD_RestAssured
    ```

3. Install the dependencies:

    ```bash
    mvn clean install
    ```

### Configuration

- `/src/test/resources/config.properties`: Contains the configuration settings such as the API endpoint, API key, and other necessary parameters.
- `/src/test/resources/extent.properties`: Configuration for ExtentReports for generating test reports.

**Important:** Make sure to **update the API key** in `config.properties` before running the test suite. Failure to do so may result in authentication errors or failed tests.


### Running Tests

To run the tests, use the following command:

```bash
mvn test
```

### Geolocation API Tests Run

---

<div style="display: flex; justify-content: space-around;">
  <img src="/assets/geolocation_api_features.png" style="width: 49%" />
  <img src="/assets/geolocation_test_run.png" style="width: 49%;" />
</div>

---