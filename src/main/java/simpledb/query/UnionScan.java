package simpledb.query;

import java.util.ArrayList;
import java.util.List;

/**
 * The scan class corresponding to the union relational
 * algebra operator.
 * The schema is the union of the schemas of the two
 * underlying scans.
 * 
 * union 연산자에 대한 Scan 클래스 예시입니다.
 * 스키마는 두 개의 기본 스캔의 스키마의 합집합입니다.
 */
public class UnionScan implements Scan {
    private List<Scan> scans;
    private int currentScan;

    /**
     * Creates a union scan having the two underlying scans.
     * 
     * @param s1 the LHS scan
     * @param s2 the RHS scan
     */
    public UnionScan(Scan s1, Scan s2) {
        scans = new ArrayList<>();
        scans.add(s1);
        scans.add(s2);
        beforeFirst();
    }

    // Scan methods

    public void beforeFirst() {
        for (Scan s : scans)
            s.beforeFirst();
        currentScan = 0;
    }

    public boolean next() {
        while (currentScan < scans.size() && !scans.get(currentScan).next()) {
            currentScan++;
        }
        return currentScan < scans.size();
    }

    public void close() {
        for (Scan s : scans)
            s.close();
    }

    public Constant getVal(String fldname) {
        return scans.get(currentScan).getVal(fldname);
    }

    public int getInt(String fldname) {
        return scans.get(currentScan).getInt(fldname);
    }

    public String getString(String fldname) {
        return scans.get(currentScan).getString(fldname);
    }

    public boolean hasField(String fldname) {
        for (Scan s : scans){
            if (s.hasField(fldname))
                return true;
        }
        return false;
    }
}
