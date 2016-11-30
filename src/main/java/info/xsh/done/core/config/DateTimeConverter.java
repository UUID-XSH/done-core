package info.xsh.done.core.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return attribute != null ? Timestamp.valueOf(attribute) : null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		return dbData != null ? dbData.toLocalDateTime() : null;
	}
}
