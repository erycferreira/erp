package com.erycferreira.enterprise.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erycferreira.enterprise.domain.exception.ReportNotFoundException;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportType;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

@ExtendWith(MockitoExtension.class)
public class GetReportUseCaseTest {

  @Mock
  private ReportRepository repository;

  @InjectMocks
  private GetReportUseCase useCase;

  @Test
  void shouldReturnReportWhenFound() {
    UUID id = UUID.randomUUID();
    Report report = new Report("Q1 Financial", ReportType.FINANCIAL);
    when(repository.findById(id)).thenReturn(Optional.of(report));

    Report result = useCase.execute(id);

    assertNotNull(result);
    assertEquals(report, result);
  }

  @Test
  void shouldThrowWhenReportNotFound() {
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(ReportNotFoundException.class, () -> useCase.execute(id));
  }
}
