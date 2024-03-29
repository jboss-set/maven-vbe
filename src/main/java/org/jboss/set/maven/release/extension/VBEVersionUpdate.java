/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2022, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.set.maven.release.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class to hold everything
 *
 * @author baranowb
 *
 */
@JsonPropertyOrder({ "groupId", "artifactId", "version", "oldVersion", "repositoryUrl", "violations" })
@JsonIgnoreProperties({"repository", "type"})
public class VBEVersionUpdate {

    private final String repositoryUrl;
    private String version;
    private final String artifactId;
    private final String groupId;
    private String oldVersion;
    private final String type;
    private final List<VBEVersionUpdate> violations = new ArrayList<>();

    public VBEVersionUpdate(final String groupId, final String artifactId, final String newVersion, final String type) {
        this.version = newVersion;
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.type = type;
        // possibly local cache?
        //TODO: add this once available
        this.repositoryUrl = "<NONE>";
    }

    public static final String generateKey(final VBEVersionUpdate entry) {
        return entry.getGroupId() + ":" + entry.getArtifactId();
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void markViolation(VBEVersionUpdate nextVersion) {
        this.violations.add(nextVersion);
    }

    public boolean hasViolations() {
        return this.violations.size() > 0;
    }

    public void setOldVersion(String version2) {
        this.oldVersion = version2;
    }

    public String getOldVersion() {
        return this.oldVersion;
    }

    public Collection<VBEVersionUpdate> getViolations() {
        return this.violations;
    }

    public String getType() {
        return this.type;
    }

    // NOTE: URL ?
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VBEVersionUpdate other = (VBEVersionUpdate) obj;
        if (artifactId == null) {
            if (other.artifactId != null)
                return false;
        } else if (!artifactId.equals(other.artifactId))
            return false;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
