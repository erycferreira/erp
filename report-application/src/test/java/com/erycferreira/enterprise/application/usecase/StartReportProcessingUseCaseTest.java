package com.erycferreira.enterprise.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
class StartReportProcessingUseCaseTest {

  @Mock
  private ReportRepository repository;

  @InjectMocks
  private StartReportProcessingUseCase useCase;

  @Test
  void shouldStartProcessingWhenReportExists() {
    UUID id = UUID.randomUUID();
    Report report = new Report("Q1 Financial", ReportType.FINANCIAL);
    when(repository.findById(id)).thenReturn(Optional.of(report));
    when(repository.save(any(Report.class))).thenReturn(report);

    assertDoesNotThrow(() -> useCase.execute(id));

    verify(repository).save(report);
  }

  @Test
  void shouldThrowWhenReportNotFound() {
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(ReportNotFoundException.class, () -> useCase.execute(id));

    verify(repository, never()).save(any());
  }
}