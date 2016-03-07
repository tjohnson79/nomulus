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

package com.google.domain.registry.testing;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.truth.Truth.assertAbout;
import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableSet;
import com.google.common.truth.AbstractVerb.DelegatedVerb;
import com.google.common.truth.FailureStrategy;
import com.google.domain.registry.model.domain.DomainApplication;
import com.google.domain.registry.model.domain.launch.ApplicationStatus;
import com.google.domain.registry.model.smd.EncodedSignedMark;
import com.google.domain.registry.testing.TruthChainer.And;

import java.util.Objects;

/** Truth subject for asserting things about {@link DomainApplication} instances. */
public final class DomainApplicationSubject
    extends AbstractDomainBaseSubject<DomainApplication, DomainApplicationSubject> {

  public And<DomainApplicationSubject> hasApplicationStatus(
      ApplicationStatus applicationStatus) {
    if (!Objects.equals(getSubject().getApplicationStatus(), applicationStatus)) {
      failWithBadResults(
          "has application status", applicationStatus, getSubject().getApplicationStatus());
    }
    return andChainer();
  }

  public And<DomainApplicationSubject> doesNotHaveApplicationStatus(
      ApplicationStatus applicationStatus) {
    return doesNotHaveValue(
        applicationStatus,
        getSubject().getApplicationStatus(),
        "application status");
  }

  public And<DomainApplicationSubject> hasExactlyEncodedSignedMarks(
      EncodedSignedMark... encodedSignedMarks) {
    if (!Objects.equals(
        ImmutableSet.copyOf(getSubject().getEncodedSignedMarks()),
        ImmutableSet.of(encodedSignedMarks))) {
      assertThat(getSubject().getEncodedSignedMarks())
          .named("the encoded signed marks of " + getDisplaySubject())
          .containsExactly((Object[]) encodedSignedMarks);
    }
    return andChainer();
  }

  public And<DomainApplicationSubject> hasNumEncodedSignedMarks(int num) {
    if (getSubject().getEncodedSignedMarks().size() != num) {
      failWithBadResults(
          "has this many encoded signed marks: ", num, getSubject().getEncodedSignedMarks().size());
    }
    return andChainer();
  }

  /** A factory for instances of this subject. */
  private static class SubjectFactory
      extends ReflectiveSubjectFactory<DomainApplication, DomainApplicationSubject>{}

  public DomainApplicationSubject(FailureStrategy strategy, DomainApplication subject) {
    super(strategy, checkNotNull(subject));
  }

  public static DelegatedVerb<DomainApplicationSubject, DomainApplication>
      assertAboutApplications() {
    return assertAbout(new SubjectFactory());
  }
}