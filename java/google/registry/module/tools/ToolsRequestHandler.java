// Copyright 2016 The Nomulus Authors. All Rights Reserved.
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

package google.registry.module.tools;

import com.google.appengine.api.users.UserService;
import google.registry.request.RequestHandler;
import javax.inject.Inject;
import javax.inject.Provider;

/** Request handler for the tools module. */
public class ToolsRequestHandler
    extends RequestHandler<ToolsRequestComponent, ToolsRequestComponent.Builder> {

  @Inject ToolsRequestHandler(
      Provider<ToolsRequestComponent.Builder> componentBuilderProvider,
      UserService userService) {
    super(componentBuilderProvider, userService);
  }
}
