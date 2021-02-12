DATE_WITH_TIME := $(shell /bin/date "+%Y%m%d%H%M%S")

CHANGELOG_FOLDER := src/main/resources/db/changelog
MASTER_FILE := ${CHANGELOG_FOLDER}/db.changelog-master.yaml

migration-%:
	$(MAKE) makeMigration \
	MIGRATION_LABEL="${MIGRATION_LABEL}" \
	PROJECT="$*"

makeMigration:
	@test "${MIGRATION_LABEL}" || (echo "MIGRATION_LABEL not set" && exit 1)
	@test "${PROJECT}" || (echo "PROJECT not set" && exit 1)
	cd ${PROJECT} && mvn liquibase:diff \
		-Dliquibase.diffChangeLogFile="${CHANGELOG_FOLDER}/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.yaml"
	@echo "  - include:" >> ${PROJECT}/${MASTER_FILE}
	@echo "      file: classpath*:db/changelog/changes/$(DATE_WITH_TIME)-$(MIGRATION_LABEL).yaml" >> ${PROJECT}/${MASTER_FILE}