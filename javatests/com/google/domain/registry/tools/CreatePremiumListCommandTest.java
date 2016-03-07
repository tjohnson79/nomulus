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

package com.google.domain.registry.tools;

import static com.google.domain.registry.request.JsonResponse.JSON_SAFETY_PREFIX;
import static com.google.domain.registry.tools.CreateOrUpdatePremiumListCommandTestCase.generateInputData;
import static com.google.domain.registry.tools.CreateOrUpdatePremiumListCommandTestCase.verifySentParams;
import static com.google.domain.registry.util.ResourceUtils.readResourceUtf8;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.google.common.base.VerifyException;
import com.google.common.collect.ImmutableMap;
import com.google.common.net.MediaType;
import com.google.domain.registry.tools.ServerSideCommand.Connection;
import com.google.domain.registry.tools.server.CreatePremiumListAction;

import com.beust.jcommander.ParameterException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/** Unit tests for {@link CreatePremiumListCommand}. */
public class CreatePremiumListCommandTest<C extends CreatePremiumListCommand>
    extends CommandTestCase<C> {

  @Mock
  Connection connection;

  String premiumTermsPath;
  String premiumTermsCsv;
  String servletPath;

  @Before
  public void init() throws Exception {
    command.setConnection(connection);
    premiumTermsPath = writeToTmpFile(readResourceUtf8(
        CreatePremiumListCommandTest.class,
        "testdata/example_premium_terms.csv"));
    servletPath = "/_dr/admin/createPremiumList";
    when(connection.send(
        eq(CreatePremiumListAction.PATH),
        anyMapOf(String.class, String.class),
        eq(MediaType.PLAIN_TEXT_UTF_8),
        any(byte[].class)))
        .thenReturn(JSON_SAFETY_PREFIX + "{\"status\":\"success\",\"lines\":[]}");
  }

  @Test
  public void testRun() throws Exception {
    ImmutableMap<String, String> params =
        ImmutableMap.of("name", "foo", "inputData", generateInputData(premiumTermsPath));
    runCommandForced("-i=" + premiumTermsPath, "-n=foo");
    assertInStdout("Successfully");
    verifySentParams(connection, servletPath, params);
  }

  @Test
  public void testRun_errorResponse() throws Exception {
    reset(connection);
    command.setConnection(connection);
    when(connection.send(
        eq(CreatePremiumListAction.PATH),
        anyMapOf(String.class, String.class),
        eq(MediaType.PLAIN_TEXT_UTF_8),
        any(byte[].class)))
        .thenReturn(JSON_SAFETY_PREFIX + "{\"status\":\"error\",\"error\":\"foo already exists\"}");
    thrown.expect(VerifyException.class, "Server error:");
    runCommandForced("-i=" + premiumTermsPath, "-n=foo");
  }

  @Test
  public void testRun_noInputFileSpecified_throwsException() throws Exception  {
    thrown.expect(ParameterException.class, "The following option is required");
    runCommand();
  }

  @Test
  public void testRun_invalidInputData() throws Exception {
    premiumTermsPath = writeToNamedTmpFile(
        "tmp_file2",
        readResourceUtf8(
            CreatePremiumListCommandTest.class, "testdata/example_invalid_premium_terms.csv"));
    thrown.expect(IllegalArgumentException.class, "Could not parse line in premium list");
    runCommandForced("-i=" + premiumTermsPath, "-n=foo");
  }
}