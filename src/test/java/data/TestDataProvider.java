package data;

import constants.AppConstants;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestDataProvider {
    /**
     * Provide test data for successful login test
     * @return Stream of Arguments containing username and password combinations
     */
    public static Stream<Arguments> provideValidCredentials() {
        return Stream.of(
                Arguments.of(AppConstants.STANDARD_USER, AppConstants.PASSWORD),
                Arguments.of(AppConstants.PERFORMANCE_GLITCH_USER, AppConstants.PASSWORD)
        );
    }

    /**
     * Provide test data for empty credentials test
     * @return Stream of Arguments containing temporary username and password combinations to be cleared
     */
    public static Stream<Arguments> provideCredentialsForEmptyTest() {
        return Stream.of(
                Arguments.of(AppConstants.WRONG_USER, AppConstants.WRONG_PASSWORD)
        );
    }

}
