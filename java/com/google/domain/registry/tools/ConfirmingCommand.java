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

import static com.google.domain.registry.tools.CommandUtilities.printLineIfNotEmpty;
import static com.google.domain.registry.tools.CommandUtilities.promptForYes;

import com.beust.jcommander.Parameter;

/** A {@link Command} that implements a confirmation step before executing. */
public abstract class ConfirmingCommand implements Command {

  @Parameter(
      names = {"-f", "--force"},
      description = "Do not prompt before executing")
  boolean force;

  @Override
  public final void run() throws Exception {
    if (checkExecutionState()) {
      init();
      printLineIfNotEmpty(prompt());
      if (force || promptForYes("Perform this command?")) {
        System.out.println(execute());
        printLineIfNotEmpty(verify());
      } else {
        System.out.println("Command aborted.");
      }
    }
  }

  /** Run any pre-execute command checks and return true if they all pass. */
  protected boolean checkExecutionState() throws Exception {
    return true;
  }

  /** Initializes the command. */
  protected void init() throws Exception {}

  /** Returns the optional extra confirmation prompt for the command. */
  protected String prompt() throws Exception {
    return "";
  }

  /** Perform the command and return a result description. */
  protected abstract String execute() throws Exception;

  /** Verify result and/or perform any post-execution steps, and return optional description. */
  protected String verify() throws Exception {
    return "";
  }
}