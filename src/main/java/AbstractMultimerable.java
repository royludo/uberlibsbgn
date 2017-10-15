public abstract class AbstractMultimerable<T extends AbstractMultimerable> extends AbstractUGlyph<T> implements CanBeMultimer{

    private MultimerFeature<T> multimerFeature;

    public AbstractMultimerable(String clazz) {
        super(clazz);
        multimerFeature = new MultimerFeature<>(this);

    }

    @Override
    public T multimer() {
        return (T) multimerFeature.multimer();
    }

    @Override
    public T multimer(boolean isMultimer) {
        return (T) multimerFeature.multimer(isMultimer);
    }

    @Override
    public boolean isMultimer() {
        return multimerFeature.isMultimer();
    }
}
