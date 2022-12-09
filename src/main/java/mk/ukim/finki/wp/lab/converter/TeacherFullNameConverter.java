package mk.ukim.finki.wp.lab.converter;

import mk.ukim.finki.wp.lab.model.TeacherFullName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TeacherFullNameConverter implements AttributeConverter<TeacherFullName, String> {
    private static final String SEPARATOR = " ";

    @Override
    public String convertToDatabaseColumn(TeacherFullName personName) {
        if (personName == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        if (personName.getSurname() != null && !personName.getSurname().isEmpty()) {
            sb.append(personName.getSurname());
            sb.append(SEPARATOR);
        }

        if (personName.getName() != null && !personName.getName().isEmpty()) {
            sb.append(personName.getName());
        }

        return sb.toString();
    }

    @Override
    public TeacherFullName convertToEntityAttribute(String dbPersonName) {
        if (dbPersonName == null || dbPersonName.isEmpty()) {
            return null;
        }

        String[] pieces = dbPersonName.split(SEPARATOR);

        if (pieces.length == 0) {
            return null;
        }

        TeacherFullName personName = new TeacherFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : "";

        if (dbPersonName.contains(SEPARATOR)) {
            personName.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
                personName.setName(pieces[1]);
            }
        } else {
            personName.setName(firstPiece);
        }

        return personName;
    }
}
