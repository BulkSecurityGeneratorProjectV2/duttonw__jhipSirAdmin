package au.gov.qld.sir.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * application_service_override_tags
 */
@Entity
@Table(name = "application_service_over_tags")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationServiceOverrideTagItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "created_date_time")
    private Instant createdDateTime;

    @Size(max = 255)
    @Column(name = "modified_by", length = 255)
    private String modifiedBy;

    @Column(name = "modified_date_time")
    private Instant modifiedDateTime;

    @Size(max = 10)
    @Column(name = "migrated_by", length = 10)
    private String migratedBy;

    @Column(name = "version")
    private Long version;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("applicationServiceOverrideTagItems")
    private ApplicationServiceOverride applicationServiceOverride;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("applicationServiceOverrideTagItems")
    private ApplicationServiceOverrideTag applicationServiceOverrideTag;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ApplicationServiceOverrideTagItems createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public ApplicationServiceOverrideTagItems createdDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public ApplicationServiceOverrideTagItems modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedDateTime() {
        return modifiedDateTime;
    }

    public ApplicationServiceOverrideTagItems modifiedDateTime(Instant modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

    public void setModifiedDateTime(Instant modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getMigratedBy() {
        return migratedBy;
    }

    public ApplicationServiceOverrideTagItems migratedBy(String migratedBy) {
        this.migratedBy = migratedBy;
        return this;
    }

    public void setMigratedBy(String migratedBy) {
        this.migratedBy = migratedBy;
    }

    public Long getVersion() {
        return version;
    }

    public ApplicationServiceOverrideTagItems version(Long version) {
        this.version = version;
        return this;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public ApplicationServiceOverride getApplicationServiceOverride() {
        return applicationServiceOverride;
    }

    public ApplicationServiceOverrideTagItems applicationServiceOverride(ApplicationServiceOverride applicationServiceOverride) {
        this.applicationServiceOverride = applicationServiceOverride;
        return this;
    }

    public void setApplicationServiceOverride(ApplicationServiceOverride applicationServiceOverride) {
        this.applicationServiceOverride = applicationServiceOverride;
    }

    public ApplicationServiceOverrideTag getApplicationServiceOverrideTag() {
        return applicationServiceOverrideTag;
    }

    public ApplicationServiceOverrideTagItems applicationServiceOverrideTag(ApplicationServiceOverrideTag applicationServiceOverrideTag) {
        this.applicationServiceOverrideTag = applicationServiceOverrideTag;
        return this;
    }

    public void setApplicationServiceOverrideTag(ApplicationServiceOverrideTag applicationServiceOverrideTag) {
        this.applicationServiceOverrideTag = applicationServiceOverrideTag;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationServiceOverrideTagItems)) {
            return false;
        }
        return id != null && id.equals(((ApplicationServiceOverrideTagItems) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApplicationServiceOverrideTagItems{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDateTime='" + getCreatedDateTime() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDateTime='" + getModifiedDateTime() + "'" +
            ", migratedBy='" + getMigratedBy() + "'" +
            ", version=" + getVersion() +
            "}";
    }
}
