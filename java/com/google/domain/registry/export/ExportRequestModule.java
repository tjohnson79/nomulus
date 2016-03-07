// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.domain.registry.export;

import static com.google.domain.registry.export.BigqueryPollJobAction.CHAINED_TASK_QUEUE_HEADER;
import static com.google.domain.registry.export.BigqueryPollJobAction.JOB_ID_HEADER;
import static com.google.domain.registry.export.BigqueryPollJobAction.PROJECT_ID_HEADER;
import static com.google.domain.registry.request.RequestParameters.extractRequiredHeader;

import com.google.domain.registry.request.Header;

import dagger.Module;
import dagger.Provides;

import javax.servlet.http.HttpServletRequest;

/** Dagger module for data export tasks. */
@Module
public final class ExportRequestModule {
  @Provides
  @Header(CHAINED_TASK_QUEUE_HEADER)
  static String provideChainedTaskQueue(HttpServletRequest req) {
    return extractRequiredHeader(req, CHAINED_TASK_QUEUE_HEADER);
  }

  @Provides
  @Header(JOB_ID_HEADER)
  static String provideJobId(HttpServletRequest req) {
    return extractRequiredHeader(req, JOB_ID_HEADER);
  }

  @Provides
  @Header(PROJECT_ID_HEADER)
  static String provideProjectId(HttpServletRequest req) {
    return extractRequiredHeader(req, PROJECT_ID_HEADER);
  }
}