package online.visionacademy.support;

public class QueryBuilder {

    public static String selectAll(final String TABLE_NAME) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        return sql.toString();
    }

    public static String selectOne(final String TABLE_NAME) {
        StringBuilder sql = new StringBuilder(selectAll(TABLE_NAME));
        sql.append(" WHERE id = ?");
        return sql.toString();
    }

    public static String selectOneWhereColumn(final String TABLE_NAME, final String COLUMN_NAME) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ");
        sql.append(COLUMN_NAME);
        sql.append(" = ? ");
         return sql.toString();
    }

    public static String selectAllById(final String TABLE_NAME, Long... ids) {
        StringBuilder sql = new StringBuilder(selectAll(TABLE_NAME));
        sql.append(" WHERE id IN ");
        sql.append(getPlaceHolders(ids));
        return sql.toString();

    }

    public static String insertQuery(final String TABLE_NAME, String... paras) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(TABLE_NAME);
        sql.append(getCols(paras));
        sql.append(" VALUES ");
        sql.append(getPlaceHolders(paras));
        return sql.toString();
    }

    public static String updateWhereId(final String TABLE_NAME, String... paras) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(TABLE_NAME);
        sql.append(" SET ");
        sql.append(buildUpdateParas(paras));
        sql.append(" WHERE id = ?");
        return sql.toString();
    }

    public static String deleteWhereId(final String TABLE_NAME) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE id = ?");
        return sql.toString();
    }

    public static String count(final String TABLE_NAME) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ");
        sql.append(TABLE_NAME);
        return sql.toString();
    }

    private static String getCols(final String... paras) {
        StringBuilder cols = new StringBuilder();
        cols.append(" (");
        for (String col : paras) {
            cols.append(col);
            cols.append(",");
        }
        cols.deleteCharAt(cols.length() - 1);
        cols.append(")");
        return cols.toString();
    }

    private static <T> String getPlaceHolders(final T... paras) {
        StringBuilder placeHolders = new StringBuilder();
        placeHolders.append(" (");
        for (T p : paras) {
            placeHolders.append("?,");
        }

        placeHolders.deleteCharAt(placeHolders.length() - 1);
        placeHolders.append(")");

        return placeHolders.toString();

    }

    private static String buildUpdateParas(final String... paras) {
        StringBuilder update = new StringBuilder();
        for (String col : paras) {
            update.append(col);
            update.append("=?,");
        }
        update.deleteCharAt(update.length() - 1);
        return update.toString();
    }
}
