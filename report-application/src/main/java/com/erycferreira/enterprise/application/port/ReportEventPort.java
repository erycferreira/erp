package com.erycferreira.enterprise.application.port;

import java.util.UUID;

public interface ReportEventPort {
  void reportCreated(UUID reportId);
}