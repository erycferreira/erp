package com.erycferreira.enterprise.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportStatus;
import com.erycferreira.enterprise.domain.model.ReportType;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

@ExtendWith(MockitoExtension.class)
class CreateReportUseCaseTest {

  @Mock
  private ReportRepository repository;

  @InjectMocks
  private CreateReportUseCase useCase;

  @Test
  void shouldCreateAndSaveReport() {
    Report saved = new Report("Q1 Financial", ReportType.FINANCIAL);
    when(repository.save(any(Report.class))).thenReturn(saved);

    Report result = useCase.execute("Q1 Financial", ReportType.FINANCIAL);

    assertNotNull(result);
    assertEquals(ReportStatus.CREATED, result.getStatus());
    verify(repository, times(1)).save(any(Report.class));
  }
}
