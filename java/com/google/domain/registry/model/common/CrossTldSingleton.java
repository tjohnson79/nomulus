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

package com.google.domain.registry.model.common;

import static com.google.domain.registry.model.common.EntityGroupRoot.getCrossTldKey;

import com.google.domain.registry.model.ImmutableObject;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/** A singleton entity in the datastore. */
public abstract class CrossTldSingleton extends ImmutableObject {

  public static final long SINGLETON_ID = 1;  // There is always exactly one of these.

  @Id
  long id = SINGLETON_ID;

  @Parent
  Key<EntityGroupRoot> parent = getCrossTldKey();
}