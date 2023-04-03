package test;

import Personel.Destination;
import Personel.Status;
import hibernate.DestinationHib;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FilterTests {

    @InjectMocks
    private DestinationHib destinationHib;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Destination> typedQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDataByFilter_success() {
        // Arrange
        Status status = Status.AVAILABLE;
        LocalDate arrivalDate = LocalDate.now();
        LocalDate departureDate = LocalDate.now().plusDays(2);
        int driverID = 1;

        List<Destination> expectedResult = new ArrayList<>();
        expectedResult.add(new Destination(100, "New York", "Los Angeles", departureDate, arrivalDate, status));

        when(entityManager.createQuery(any(String.class), any(Class.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(any(String.class), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedResult);

        // Act
        List<Destination> result = destinationHib.getDataByFilter(status, arrivalDate, departureDate, driverID);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getDataByFilter_noResult() {
        // Arrange
        Status status = Status.AVAILABLE;
        LocalDate arrivalDate = LocalDate.now();
        LocalDate departureDate = LocalDate.now().plusDays(2);
        int driverID = 1;

        when(entityManager.createQuery(any(String.class), any(Class.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(any(String.class), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenThrow(NoResultException.class);

        // Act
        List<Destination> result = destinationHib.getDataByFilter(status, arrivalDate, departureDate, driverID);

        // Assert
        assertEquals(0, result.size());
    }
}
